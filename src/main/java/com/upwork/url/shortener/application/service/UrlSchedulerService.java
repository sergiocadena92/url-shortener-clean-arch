package com.upwork.url.shortener.application.service;

import com.upwork.url.shortener.application.port.input.UrlSchedulerServicePort;
import com.upwork.url.shortener.application.port.output.UrlPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlSchedulerService implements UrlSchedulerServicePort {

    @Value("${url.persistence.retention.hours}")
    private long retentionHours;

    private final UrlPersistencePort urlPersistencePort;


    @Scheduled(cron = "${url.persistence.purge.cron}")
    public void removeOldRecords() {
        log.info("Start deleting old records");
        urlPersistencePort.deleteByCreationTime(LocalDateTime.now().minusHours(retentionHours));
        log.info("Finish deleting old records");
    }

}
