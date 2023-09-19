package com.basereh.springlibrary.util;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public record ExceptionData(Exception content, Integer status) {
}
