package com.tds.url.controller.swagger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import com.tds.url.dto.StatisticsRequestDTO;
import com.tds.url.dto.UrlRequestDTO;
import com.tds.url.dto.UrlResponseDTO;
import com.tds.url.dto.UrlShortDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "URL Shortener API")
public interface UrlControllerSwagger {

    @Operation(summary = "Shorten", description = "Shorten the original URL", method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Input Json",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UrlRequestDTO.class)
                    )))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Returns the shortened URL",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UrlShortDTO.class)
            ))})
    public ResponseEntity<UrlShortDTO> urlShortener(@RequestBody UrlRequestDTO request, HttpServletRequest httpRequest);

    @Operation(summary = "Statistics", description = "Display access statistics for the shortened URL", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Displays access statistics for the shortened URL - complete URL, total overall, average daily accesses and date of first access.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = StatisticsRequestDTO.class)
            )),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request - no JSON in the response",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The provided URL was not found.",
                    content = @Content
            )
    })
    public ResponseEntity<UrlResponseDTO> statisticsShortener(@RequestBody StatisticsRequestDTO request);

    @Operation(summary = "Statistics", description = "Redirects to the original URL", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation, you will be redirected to the URL",
                    content = @Content(
                            mediaType = "text/html",
                            schema = @Schema(
                                    example = "<html><body><h1>HTML</h1></body></html>"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The provided URL was not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "The provided URL was not found.")
                    )
            )})
    public RedirectView accessUrl(@PathVariable String shortened);

}