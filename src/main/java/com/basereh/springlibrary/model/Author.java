package com.basereh.springlibrary.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Author {
    private Integer id;
    private String firstname;
    private String lastname;

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}