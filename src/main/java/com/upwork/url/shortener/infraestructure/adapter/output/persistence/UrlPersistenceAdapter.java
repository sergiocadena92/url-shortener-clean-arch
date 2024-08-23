package com.upwork.url.shortener.infraestructure.adapter.output.persistence;

import com.upwork.url.shortener.application.port.output.UrlPersistencePort;
import com.upwork.url.shortener.domain.model.Url;
import com.upwork.url.shortener.infraestructure.adapter.output.persistence.mapper.UrlPersistenceMapper;
import com.upwork.url.shortener.infraestructure.adapter.output.persistence.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UrlPersistenceAdapter implements UrlPersistencePort {

    private final UrlRepository urlRepository;

    private final UrlPersistenceMapper urlPersistenceMapper;

    @Override
    public void save(Url url) {
        urlRepository.save(urlPersistenceMapper.toUrlEntity(url));
    }

    @Override
    public Optional<Url> findByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .map(urlPersistenceMapper::toUrl);
    }

    @Override
    public void deleteByCreationTime(LocalDateTime creationTime) {
        urlRepository.deleteByCreationTime(creationTime);
    }
}
