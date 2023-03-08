package com.momo.admin.user.exception;

import com.momo.admin.common.exception.CustomException;
import com.momo.admin.common.exception.enums.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
