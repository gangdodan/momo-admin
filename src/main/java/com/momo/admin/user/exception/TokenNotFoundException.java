package com.momo.admin.user.exception;

import com.momo.admin.common.exception.CustomException;
import com.momo.admin.common.exception.enums.ErrorCode;

public class TokenNotFoundException extends CustomException {
    public TokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
