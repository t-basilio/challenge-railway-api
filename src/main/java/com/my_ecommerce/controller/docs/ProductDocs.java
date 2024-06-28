package com.my_ecommerce.controller.docs;

import com.my_ecommerce.controller.dtos.ProductDto;
import com.my_ecommerce.domain.model.Product;
import com.my_ecommerce.service.exception.BusinessException;
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

@Tag(name = "Products", description = "RESTful API for managing products.")
public interface ProductDocs {

    @Operation(summary = "Get all products", description = "Retrieve a list of all registered products", tags = {"Products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))}
            )})
    ResponseEntity<List<ProductDto>> listing();

    @Operation(summary = "Get a product by ID", description = "Retrieve a specific product based on its ID", tags = { "Products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Product not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<ProductDto> finding(Long id);

    @Operation(summary = "Create a new product", description = "Create a new product and return the created product's data", tags = { "Products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<ProductDto> creating(ProductDto productDto);

    @Operation(summary = "Update a product", description = "Update the data of an existing product based on its ID", tags = { "Products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Product not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<ProductDto> updating(@PathVariable Long id, @RequestBody ProductDto productDto);

    @Operation(summary = "Delete a product", description = "Delete an existing product based on its ID", tags = { "Products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    ResponseEntity<Void> deleting(Long id);

}
