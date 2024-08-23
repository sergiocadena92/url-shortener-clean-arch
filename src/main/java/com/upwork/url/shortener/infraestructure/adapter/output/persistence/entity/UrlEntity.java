package com.upwork.url.shortener.infraestructure.adapter.output.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("url")
@Getter
@Builder
public class UrlEntity {
    @Id
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime creationTime;
}
