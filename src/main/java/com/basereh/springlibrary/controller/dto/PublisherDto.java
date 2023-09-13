package com.basereh.springlibrary.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PublisherDto {
    private Long id;
    public String name;
}
