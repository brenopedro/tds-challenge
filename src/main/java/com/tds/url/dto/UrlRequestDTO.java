package com.tds.url.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Input object for the shortenUrl endpoint")
public class UrlRequestDTO {
	
    @Schema(description = "URL Complete", example = "https://google.com")
	private String url;
}
