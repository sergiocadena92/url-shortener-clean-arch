package com.upwork.url.shortener.application.port.input;

import com.upwork.url.shortener.domain.exception.UrlNotFoundException;

public interface UrlServicePort {

    String shortenUrl(String originalUrl);

    String getOriginalUrl(String shortUrl) throws UrlNotFoundException;
}
