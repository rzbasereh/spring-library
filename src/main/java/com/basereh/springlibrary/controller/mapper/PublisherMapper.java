package com.basereh.springlibrary.controller.mapper;

import com.basereh.springlibrary.controller.dto.PublisherDto;
import com.basereh.springlibrary.model.Publisher;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PublisherMapper {
    PublisherDto toDto(Publisher publisher);

    Publisher toModel(PublisherDto publisherDto);
}
