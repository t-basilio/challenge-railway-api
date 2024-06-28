package com.my_ecommerce.controller.docs;

import com.my_ecommerce.controller.dtos.ClientDto;
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

@Tag(name = "Clients", description = "RESTful API for managing clients.")
public interface ClientDocs {

    @Operation(summary = "Get all clients", description = "Retrieve a list of all registered clients", tags = {"Clients"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))}
            )})
    ResponseEntity<List<ClientDto>> listing();

    @Operation(summary = "Get a client by ID", description = "Retrieve a specific client based on its ID", tags = { "Clients" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Client not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<ClientDto> finding(Long id);

    @Operation(summary = "Create a new client", description = "Create a new client and return the created client's data", tags = { "Clients" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid client data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<ClientDto> creating(ClientDto clientDto);

    @Operation(summary = "Update a client", description = "Update the data of an existing client based on its ID", tags = { "Clients" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Client not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid client data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<ClientDto> updating(@PathVariable Long id, @RequestBody ClientDto clientDto);

    @Operation(summary = "Delete a client", description = "Delete an existing client based on its ID", tags = { "Clients" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")})
    ResponseEntity<Void> deleting(Long id);

}
