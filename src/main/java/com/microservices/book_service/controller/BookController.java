package com.microservices.book_service.controller;

import com.microservices.book_service.model.BookDto;
import com.microservices.book_service.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
@Tag(name = "Currency", description = "Endpoint for managing currencies")
@RequiredArgsConstructor
public class BookController {
    
    private final BookService service;
    
    @GetMapping(value = "/{id}/{currency}",  produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "None for now",
            summary = "None for now", responses = {
            @ApiResponse(description = "SUCCESS", responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = BookDto.class))}),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
    public BookDto findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency ) {
        return service.findBook(id, currency);
    }
}
