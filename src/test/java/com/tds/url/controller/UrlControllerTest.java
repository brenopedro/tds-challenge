package com.tds.url.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import com.tds.url.dto.StatisticsRequestDTO;
import com.tds.url.dto.UrlRequestDTO;
import com.tds.url.dto.UrlResponseDTO;
import com.tds.url.dto.UrlShortDTO;
import com.tds.url.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Description;

@ExtendWith(MockitoExtension.class)
class UrlControllerTest {

    @InjectMocks
    UrlController shortenerController;
    @Mock
    UrlService urlService;
    @Mock
    HttpServletRequest httpRequest;

    String urlShort = "123b";
    String urlComplete = "https://shortenUrl.com";
    StatisticsRequestDTO statisticsDto = new StatisticsRequestDTO();
    

    @BeforeEach
    void setUp() {
        statisticsDto.setShortened(urlShort);
    }

    @Test
    @Description(value = "Shorten the original URL")
    void shortenUrlTest() {
    	UrlRequestDTO dto = new UrlRequestDTO();
    	dto.setUrl(urlComplete);

        when(urlService.shortenUrl(dto)).thenReturn(urlShort);

        when(httpRequest.getScheme()).thenReturn("http");
        when(httpRequest.getServerName()).thenReturn("localhost");
        when(httpRequest.getServerPort()).thenReturn(8080);

        ResponseEntity<UrlShortDTO> result = shortenerController.urlShortener(dto, httpRequest);

        assertNotNull(result);
        assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
    }

    @Test
    @Description(value = "Returns the access statistics when the shortened URL is found")
    void accessStatisticsWhenUrlShortFoundTest() {
        ResponseEntity<UrlResponseDTO> result = shortenerController.statisticsShortener(statisticsDto);

        assertNotNull(result);
        assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
    }

    @Test
    @Description(value = "Redirects to the original URL")
    void redirectOriginalURLWhenShortenedFoundTest() {
    	when(urlService.accessUrl(anyString())).thenReturn(urlComplete);
    	
    	RedirectView redirectView = shortenerController.accessUrl(urlShort);

        assertNotNull(redirectView);
        assertEquals(urlComplete, redirectView.getUrl());
        verify(urlService, times(1)).accessUrl(urlShort);
    }
}