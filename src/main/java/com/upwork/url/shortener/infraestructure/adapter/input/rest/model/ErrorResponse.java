package com.upwork.url.shortener.infraestructure.adapter.input.rest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
public class ErrorResponse {

    private String code;
    private String message;
    private List<String> details;
    private LocalDateTime dateTime;
}
