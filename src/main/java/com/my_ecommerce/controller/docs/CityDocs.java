package com.my_ecommerce.controller.docs;

import com.my_ecommerce.controller.dtos.CityDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Cities", description = "RESTful API for managing cities.")
public interface CityDocs {

    @Operation(summary = "Get all cities", description = "Retrieve a list of all registered cities", tags = {"Cities"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CityDto.class))}
            )})
    ResponseEntity<List<CityDto>> listing();

    @Operation(summary = "Get a city by ID", description = "Retrieve a specific city based on its ID", tags = { "Cities" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CityDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "City not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<CityDto> finding(Long id);

    @Operation(summary = "Create a new city", description = "Create a new city and return the created city's data", tags = { "Cities" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "City created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CityDto.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid city data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<CityDto> creating(CityDto cityDto);

    @Operation(summary = "Update a city", description = "Update the data of an existing city based on its ID", tags = { "Cities" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "City updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CityDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "City not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid city data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<CityDto> updating(@PathVariable Long id, @RequestBody CityDto cityDto);

    @Operation(summary = "Delete a city", description = "Delete an existing city based on its ID", tags = { "Cities" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "City deleted successfully"),
            @ApiResponse(responseCode = "404", description = "City not found")})
    ResponseEntity<Void> deleting(Long id);

}
