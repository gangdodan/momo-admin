package com.momo.admin.payment.domain;


import com.momo.admin.common.exception.CustomException;
import com.momo.admin.common.exception.enums.ErrorCode;

public class PaymentException extends CustomException {
    public PaymentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
