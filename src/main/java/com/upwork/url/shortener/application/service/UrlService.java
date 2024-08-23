package com.upwork.url.shortener.application.service;

import com.upwork.url.shortener.application.port.input.UrlServicePort;
import com.upwork.url.shortener.application.port.output.UrlPersistencePort;
import com.upwork.url.shortener.configuration.CaffeineCacheConfig;
import com.upwork.url.shortener.domain.exception.UrlNotFoundException;
import com.upwork.url.shortener.domain.model.Url;
import com.upwork.url.shortener.util.UrlSanitizer;
import com.upwork.url.shortener.util.UrlShortener;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService implements UrlServicePort {

    @Value("${url.shortener.base}")
    private String base;

    private final UrlPersistencePort urlPersistencePort;

    @Transactional
    public String shortenUrl(String originalUrl) {
        final String encodedUrl = UrlSanitizer.encodeUrl(originalUrl);
        final String shortUrl = UrlShortener.shortenUrl(encodedUrl);
        final Url url = buildUrl(encodedUrl, shortUrl);
        urlPersistencePort.save(url);
        return String.format(base, shortUrl);
    }

    private static Url buildUrl(String sanitizedUrl, String shortUrl) {
        return Url.builder()
                .originalUrl(sanitizedUrl)
                .shortUrl(shortUrl)
                .creationTime(LocalDateTime.now())
                .build();
    }

    @Cacheable(value = CaffeineCacheConfig.URLS, key = "#shortUrl", unless = "#result == null")
    public String getOriginalUrl(String shortUrl) throws UrlNotFoundException {
        return urlPersistencePort.findByShortUrl(shortUrl)
                .map(Url::getOriginalUrl)
                .map(UrlSanitizer::decodeUrl)
                .orElseThrow(UrlNotFoundException::new);
    }

}
