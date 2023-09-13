package com.basereh.springlibrary.controller.mapper;

import com.basereh.springlibrary.model.Author;
import com.basereh.springlibrary.controller.dto.AuthorDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorMapper {
    AuthorDto toDto(Author author);

    Author toModel(AuthorDto authorDto);
}
