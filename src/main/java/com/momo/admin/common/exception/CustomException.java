package com.momo.admin.common.exception;

import com.momo.admin.common.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CustomException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
