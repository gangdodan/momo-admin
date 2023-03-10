package com.momo.admin.settlement.exception;

import com.momo.admin.common.exception.CustomException;
import com.momo.admin.common.exception.enums.ErrorCode;

public class CanNotSettleException extends CustomException {
    public CanNotSettleException(ErrorCode errorCode) {
        super(errorCode,errorCode.getMessage());
    }
}
