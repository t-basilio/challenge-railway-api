package com.my_ecommerce.controller.docs;

import com.my_ecommerce.controller.dtos.StateDto;
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

@Tag(name = "States", description = "RESTful API for managing states.")
public interface StateDocs {

    @Operation(summary = "Get all states", description = "Retrieve a list of all registered states", tags = {"States"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StateDto.class))}
            )})
    ResponseEntity<List<StateDto>> listing();

    @Operation(summary = "Get a state by ID", description = "Retrieve a specific state based on its ID", tags = { "States" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StateDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "State not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<StateDto> finding(Long id);

    @Operation(summary = "Create a new state", description = "Create a new state and return the created state's data", tags = { "States" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "State created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StateDto.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid state data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<StateDto> creating(StateDto stateDto);

    @Operation(summary = "Update a state", description = "Update the data of an existing state based on its ID", tags = { "States" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "State updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StateDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "State not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid state data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<StateDto> updating(@PathVariable Long id, @RequestBody StateDto stateDto);

    @Operation(summary = "Delete a state", description = "Delete an existing state based on its ID", tags = { "States" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "State deleted successfully"),
            @ApiResponse(responseCode = "404", description = "State not found")})
    ResponseEntity<Void> deleting(Long id);

}
