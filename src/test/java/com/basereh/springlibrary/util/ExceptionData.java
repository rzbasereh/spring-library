package com.basereh.springlibrary.util;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ExceptionData {
     final private Exception content;
     final private Integer status;
}
