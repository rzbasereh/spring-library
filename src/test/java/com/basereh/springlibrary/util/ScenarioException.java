package com.basereh.springlibrary.util;

import io.cucumber.spring.ScenarioScope;
import lombok.Getter;

@Getter
@ScenarioScope
public class ScenarioException {
    private ExceptionData exception;

    public void setException(Exception content, Integer status) {
        exception = ExceptionData.builder().content(content).status(status).build();
    }
}
