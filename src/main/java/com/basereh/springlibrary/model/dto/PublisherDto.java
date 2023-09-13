package com.basereh.springlibrary.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PublisherDto {
    private Integer id;
    public String name;
}
