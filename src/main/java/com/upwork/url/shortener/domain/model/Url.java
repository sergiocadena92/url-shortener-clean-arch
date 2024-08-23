package com.upwork.url.shortener.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Url {
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime creationTime;
}

