package com.basereh.springlibrary.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookDto {
    private Integer id;
    public String name;
    public PublisherDto publisher;
    public AuthorDto author;
}
