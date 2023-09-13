package com.basereh.springlibrary.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Publisher {
    private Integer id;
    public String name;

    @Override
    public String toString() {
        return name;
    }
}
