package com.microservices.book_service.util;

import com.microservices.book_service.model.BookDto;
import com.microservices.book_service.model.entity.Book;

public class Mapper {
    
    public static BookDto bookEntityMapper(Book entity) {
        return BookDto.builder()
                      .id(entity.getId())
                      .author(entity.getAuthor())
                      .title(entity.getTitle())
                      .launchDate(entity.getLaunchDate())
                      .price(entity.getPrice())
                      .build();
    }
    
    public static Book bookDtoMapper(BookDto dto) {
        return Book.builder()
                   .id(dto.getId())
                   .author(dto.getAuthor())
                   .title(dto.getTitle())
                   .launchDate(dto.getLaunchDate())
                   .price(dto.getPrice())
                   .build();
    }
}
