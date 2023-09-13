package com.basereh.springlibrary.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorDto {
    private Integer id;
    private String firstname;
    private String lastname;
}