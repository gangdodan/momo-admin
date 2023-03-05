package com.momo.admin.user.exception;

public class TokenNotFoundException extends CustomException {
    public TokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
