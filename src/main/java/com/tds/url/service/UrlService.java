package com.tds.url.service;

import com.tds.url.dto.UrlRequestDTO;
import com.tds.url.dto.UrlResponseDTO;

public interface UrlService {
	

	public String shortenUrl(UrlRequestDTO dto);

	public String accessUrl(String shortened);
	
	public UrlResponseDTO accessStatistics(String shortened);
}
