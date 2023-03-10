package com.momo.admin.common.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    UNABLE_TO_PROCESS(SERVICE_UNAVAILABLE, "현재 해당 요청을 수행할 수 없습니다, 관리자에게 문의하세요."),
    DATA_NOT_FOUND(NOT_FOUND, "조회하려는 데이터가 존재하지 않습니다."),
    REQUEST_CONFLICT(FORBIDDEN, "권한이 없습니다."),
    REQUEST_DENIED(FORBIDDEN, "권한이 없습니다."),
    CAN_NOT_CHANGE_SETTLEMENT_STATE(CONFLICT,"정산의 상태를 변경할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;


}
