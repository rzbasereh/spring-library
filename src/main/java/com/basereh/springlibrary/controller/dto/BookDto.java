package com.basereh.springlibrary.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class BookDto {
    private Integer id;
    public String name;
    public PublisherDto publisher;
    public Set<AuthorDto> authors;
}
