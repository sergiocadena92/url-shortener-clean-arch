package com.upwork.url.shortener.application.port.output;

import com.upwork.url.shortener.domain.model.Url;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UrlPersistencePort {

    void save(Url url);

    Optional<Url> findByShortUrl(String shortUrl);

    void deleteByCreationTime(LocalDateTime creationTime);
}
