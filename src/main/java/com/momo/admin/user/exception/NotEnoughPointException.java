package com.momo.admin.user.exception;

import com.momo.admin.common.exception.CustomException;
import com.momo.admin.common.exception.enums.ErrorCode;

public class NotEnoughPointException extends CustomException {
    public NotEnoughPointException(ErrorCode errorCode) {
        super(errorCode);
    }
}
