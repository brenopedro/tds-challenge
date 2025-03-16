package com.tds.url.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.tds.url.controller.swagger.UrlControllerSwagger;
import com.tds.url.dto.StatisticsRequestDTO;
import com.tds.url.dto.UrlRequestDTO;
import com.tds.url.dto.UrlResponseDTO;
import com.tds.url.dto.UrlShortDTO;
import com.tds.url.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UrlController implements UrlControllerSwagger {
	
	@Autowired
	private UrlService service;
	
	@Override
	@PostMapping(path = "/")
	public ResponseEntity<UrlShortDTO> urlShortener(@RequestBody UrlRequestDTO request, HttpServletRequest httpRequest) {
		 String urlShort = service.shortenUrl(request);

		 String hostUrl = String.format("%s://%s:%d/", httpRequest.getScheme(), httpRequest.getServerName(), httpRequest.getServerPort());

		 return ResponseEntity.ok(new UrlShortDTO(hostUrl + urlShort));
	}
	
	@Override
	@GetMapping(path = "/{shortened}")
	public RedirectView accessUrl(@PathVariable String shortened) {
		String url = service.accessUrl(shortened);
		
		return new RedirectView(url);
	}
	
	@Override
	@PostMapping(path = "/statistics")
	public ResponseEntity<UrlResponseDTO> statisticsShortener(@RequestBody StatisticsRequestDTO request) {
		UrlResponseDTO response = service.accessStatistics(request.getShortened().substring(request.getShortened().lastIndexOf("/")+1));
		return ResponseEntity.ok(response);
	}

}
