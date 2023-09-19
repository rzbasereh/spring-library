package com.basereh.springlibrary.controller.dto;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
public class AuthorDto {
    private Long id;
    private String firstname;
    private String lastname;
}