package com.tds.url.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Input object for the statistics")
public class StatisticsRequestDTO {
	
    @Schema(description = "Shorten url", example = "http://localhost:8080/ur8102")
	private String shortened;
}
