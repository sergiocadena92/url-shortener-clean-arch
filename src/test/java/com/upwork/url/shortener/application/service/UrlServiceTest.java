package com.upwork.url.shortener.application.service;

import com.upwork.url.shortener.domain.model.Url;
import com.upwork.url.shortener.infraestructure.adapter.output.persistence.UrlPersistenceAdapter;
import com.upwork.url.shortener.domain.exception.UrlNotFoundException;
import com.upwork.url.shortener.util.UrlSanitizer;
import com.upwork.url.shortener.util.UrlShortener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    @InjectMocks
    private UrlService urlService;

    @Mock
    private UrlPersistenceAdapter urlPersistenceAdapter;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(urlService, "base", "http://short.url/%s");
    }

    @Test
    void testShortenUrl() {
        String originalUrl = "http://example.com";
        String encodedUrl = "http%3A%2F%2Fexample.com";
        String shortUrl = "abc123";

        mockStatic(UrlShortener.class);

        when(UrlSanitizer.encodeUrl(originalUrl)).thenReturn(encodedUrl);
        when(UrlShortener.shortenUrl(encodedUrl)).thenReturn(shortUrl);

        String result = urlService.shortenUrl(originalUrl);

        assertEquals("http://short.url/abc123", result);
        verify(urlPersistenceAdapter, times(1)).save(any(Url.class));
    }

    @Test
    void testGetOriginalUrl() throws UrlNotFoundException {
        String shortUrl = "abc123";
        String encodedUrl = "http%3A%2F%2Fexample.com";
        Url url = Url.builder()
                .originalUrl(encodedUrl)
                .shortUrl(shortUrl)
                .creationTime(LocalDateTime.now())
                .build();

        when(urlPersistenceAdapter.findByShortUrl(shortUrl)).thenReturn(Optional.of(url));
        mockStatic(UrlSanitizer.class);
        when(UrlSanitizer.decodeUrl(encodedUrl)).thenReturn("http://example.com");

        String result = urlService.getOriginalUrl(shortUrl);

        assertEquals("http://example.com", result);
    }

    @Test
    public void testGetOriginalUrlNotFound() {
        String shortUrl = "abc123";
        when(urlPersistenceAdapter.findByShortUrl(shortUrl)).thenReturn(Optional.empty());

        assertThrows(UrlNotFoundException.class, () -> urlService.getOriginalUrl(shortUrl));
    }
}