package com.basereh.springlibrary.controller.mapper;

import com.basereh.springlibrary.controller.dto.BookDto;
import com.basereh.springlibrary.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(BookDto bookDto);
}
