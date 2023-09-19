package com.basereh.springlibrary.util;

import lombok.Getter;

@Getter
public class ScenarioException {
    private ExceptionData exception;

    public void setException(Exception content, Integer status) {
        exception = ExceptionData.builder().content(content).status(status).build();
    }
}
