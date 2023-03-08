package com.momo.admin.user.exception;

import com.momo.admin.common.exception.CustomException;
import com.momo.admin.common.exception.enums.ErrorCode;
import lombok.RequiredArgsConstructor;

public class CanNotChangeUserStateException extends CustomException {
    public CanNotChangeUserStateException(ErrorCode errorCode) {
         super(errorCode,errorCode.getMessage());
    }
}
