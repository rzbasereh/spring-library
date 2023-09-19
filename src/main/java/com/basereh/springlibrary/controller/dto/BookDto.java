package com.basereh.springlibrary.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode
public class BookDto {
    private Integer id;
    public String name;
    public PublisherDto publisher;
    public Set<AuthorDto> authors;
}
