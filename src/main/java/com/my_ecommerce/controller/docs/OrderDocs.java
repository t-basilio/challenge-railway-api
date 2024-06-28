package com.my_ecommerce.controller.docs;

import com.my_ecommerce.controller.dtos.InputItem;
import com.my_ecommerce.controller.dtos.ItemOrderId;
import com.my_ecommerce.controller.dtos.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Orders", description = "RESTful API for managing orders.")
public interface OrderDocs {

    @Operation(summary = "Get all orders", description = "Retrieve a list of all registered orders", tags = {"Orders"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))}
            )})
    ResponseEntity<List<OrderDto>> listing();

    @Operation(summary = "Get an order by ID", description = "Retrieve a specific order based on its ID", tags = { "Orders" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Order not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<OrderDto> finding(Long id);

    @Operation(summary = "Create a new order", description = "Create a new order and return the created order's data", tags = { "Orders" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))}
            ),
            @ApiResponse(responseCode = "422", description = "Invalid order data provided", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}
            )})
    ResponseEntity<OrderDto> creating(OrderDto orderDto);

    @Operation(summary = "Add an item on order", description = "Insert an item by ID, quantity and Product ID", tags = { "Orders" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item inserted successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "400", description = "Product ID not founded")})
    ResponseEntity<Void> addingItem(Long id, InputItem item);

    @Operation(summary = "Remove an item from order", description = "Delete an item by ID and Item ID", tags = { "Orders" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item removed successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "400", description = "Item ID not founded")})
    ResponseEntity<Void> removingItem(Long id, ItemOrderId item);

    @Operation(summary = "Finish an order", description = "Change status from order by ID to FINISHED", tags = { "Orders" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Finished order successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "400", description = "Cannot change status from order")})
    ResponseEntity<Void> finishing(Long id);

    @Operation(summary = "Cancel an order", description = "Change status from order by ID to CANCELLED", tags = { "Orders" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cancelled order successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "400", description = "Cannot change status from order")})
    ResponseEntity<Void> cancelling(Long id);

}
