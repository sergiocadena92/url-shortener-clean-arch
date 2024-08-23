package com.upwork.url.shortener.application.service;

import static org.junit.jupiter.api.Assertions.*;


import com.upwork.url.shortener.application.port.output.UrlPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UrlSchedulerServiceTest {

    @Mock
    private UrlPersistencePort urlPersistencePort;

    @InjectMocks
    private UrlSchedulerService urlSchedulerService;

    @Test
    public void testRemoveOldRecords() {
        urlSchedulerService.removeOldRecords();
        verify(urlPersistencePort, times(1)).deleteByCreationTime(any(LocalDateTime.class));
    }
}