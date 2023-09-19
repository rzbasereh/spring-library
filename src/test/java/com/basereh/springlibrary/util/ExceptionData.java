package com.basereh.springlibrary.util;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ExceptionData {
    private final Exception content;
    private final Integer status;
}
