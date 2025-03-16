package com.tds.url.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.url.dto.UrlRequestDTO;
import com.tds.url.dto.UrlResponseDTO;
import com.tds.url.exception.UrlNotFoundException;
import com.tds.url.model.UrlModel;
import com.tds.url.repository.UrlRepository;
import com.tds.url.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService {
	
	@Autowired
	private UrlRepository repository;

	@Override
	public String shortenUrl(UrlRequestDTO dto) {
		Optional<UrlModel> model = repository.findByUrl(dto.getUrl());
		
		if (model.isPresent()) return model.get().getShortened();
		
		String shortened = UUID.randomUUID().toString().substring(0,6);
		
		repository.save(new UrlModel(dto.getUrl(), shortened));
		
		return shortened;
	}

	@Override
	public String accessUrl(String shortened) {
		UrlModel model = getUrlModel(shortened);
		
		accessedUrl(model);
		
		return model.getUrl();
		
	}
	
	@Override
	public UrlResponseDTO accessStatistics(String shortened) {
		UrlModel model = getUrlModel(shortened);
		
		double averageAccess = calculateDailyAccess(model);
		UrlResponseDTO dto = new UrlResponseDTO(model.getUrl(), shortened, model.getAccessCount(), model.getDateFirstAccess(), averageAccess);
		
		return dto;
	}

	private double calculateDailyAccess(UrlModel model) {
		long days = 0;
		if (model.getDailyAccessAverage() != null)
			days = ChronoUnit.DAYS.between(model.getDateFirstAccess(), LocalDate.now()) + 1;
		else
			days = 1;
		
		return model.getAccessCount() / days;
		
	}

	private void accessedUrl(UrlModel model) {
		Long accessCount = model.getAccessCount();
		
		if (accessCount == 0L) {
			model.setDateFirstAccess(LocalDate.now());
		} 
		
		model.setAccessCount(accessCount + 1L);
		repository.save(model);
		
	}

	private UrlModel getUrlModel(String shortened) {
		return repository.findByShortened(shortened)
				.orElseThrow(() -> new UrlNotFoundException("Url não encontrada", 404));
	}

}
