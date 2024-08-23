package com.upwork.url.shortener.util;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UrlEntityShortenerTest {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 8;

    @BeforeEach
    void setUp() {
        UrlShortener.setAlphabet(ALPHABET);
        UrlShortener.setLength(LENGTH);
    }

    @Test
    void testShortenUrlNotNull() {
        String url = "https://example.com";
        String shortUrl = UrlShortener.shortenUrl(url);

        assertNotNull(shortUrl);
    }

    @Test
    void testShortenUrlCorrectLength() {
        String url = "https://example.com";
        String shortUrl = UrlShortener.shortenUrl(url);

        assertEquals(LENGTH, shortUrl.length());
    }

    @Test
    void testShortenUrlOnlyContainsAlphabetCharacters() {
        String url = "https://example.com";
        String shortUrl = UrlShortener.shortenUrl(url);

        for (char c : shortUrl.toCharArray()) {
            assertTrue(ALPHABET.indexOf(c) >= 0);
        }
    }

}
