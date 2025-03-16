package com.tds.url.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import com.tds.url.dto.UrlRequestDTO;
import com.tds.url.model.UrlModel;
import com.tds.url.repository.UrlRepository;

@ExtendWith(MockitoExtension.class)
class UrlServiceImplTest {

    @InjectMocks
    UrlServiceImpl service;

    @Mock
    UrlRepository urlRepository;

    UrlModel url;
    String urlShort = "123b";
    String urlComplete = "https://shortenUrl.com";
    UrlRequestDTO urlRequestDTO = new UrlRequestDTO();

    @BeforeEach
    void setUp() {
        url = new UrlModel(1L, urlComplete, urlShort, 1265L, LocalDate.now(), 1.1443);
        urlRequestDTO.setUrl(urlComplete);
    }

    @Test
    @Description(value = "Creates and returns the shortened URL")
    void shouldCreateAndReturnNewShortenedURLTest() {
        when(urlRepository.findByUrl(anyString())).thenReturn(Optional.empty());

        String result = service.shortenUrl(urlRequestDTO);

        assertNotNull(result);
    }

    @Test
    @Description(value = "Returns the existing shortened URL")
    void shouldReturnExistingShortenedURLTest() {
        when(urlRepository.findByUrl(anyString())).thenReturn(Optional.of(url));

        String result = service.shortenUrl(urlRequestDTO);

        assertNotNull(result);
        assertEquals("123b", result);
    }


    @Test
    @Description(value = "Returns the access statistics")
    void accessStatisticsTest() {
        when(urlRepository.findByShortened(anyString())).thenReturn(Optional.of(url));

        service.accessStatistics(urlShort);

        assertEquals(1265, url.getAccessCount());
        verify(urlRepository, times(1)).findByShortened(urlShort);
    }

    @Test
    @Description(value = "Returns the complete url")
    void accessUrl() {
        when(urlRepository.findByShortened(anyString())).thenReturn(Optional.of(url));

        service.accessUrl(urlShort);

        verify(urlRepository, times(1)).findByShortened(urlShort);
    }
}