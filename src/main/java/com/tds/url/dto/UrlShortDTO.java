package com.tds.url.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Output object for the shortenUrl endpoint")
public class UrlShortDTO {

    @Schema(description = "URL Short", example = "http://localhost:8080/110aa1")
    private String urlShort;

}