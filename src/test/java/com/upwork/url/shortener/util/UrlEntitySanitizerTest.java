package com.upwork.url.shortener.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UrlEntitySanitizerTest {


    @Test
    void testEncodeDecodeUrlRoundTrip() {
        String url = "https://example.com/path?name=John Doe&age=30";
        String encodedUrl = UrlSanitizer.encodeUrl(url);
        String decodedUrl = UrlSanitizer.decodeUrl(encodedUrl);

        assertEquals(url, decodedUrl);
    }
}