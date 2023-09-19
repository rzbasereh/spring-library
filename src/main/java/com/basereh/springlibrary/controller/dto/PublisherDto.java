package com.basereh.springlibrary.controller.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class PublisherDto {
    private Long id;
    public String name;
}
