package com.tds.url.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Output object for the accessStatistics endpoint")
public class UrlResponseDTO {
	
	@Schema(description = "URL Complete", example = "https://google.com")
    String urlComplete;
	@Schema(description = "URL shortened", example = "http://localhost:8080/shoi32")
	private String urlShortened;
	@Schema(description = "Access Count", example = "150")
	private Long accessCount;
	@Schema(description = "Date Of First Access", example = "2024-05-02")
	private LocalDate dateFirstAccess;
    @Schema(description = "Daily Access Average", example = "0.1234")
    private Double dailyAccessAverage;
}
