package com.jejuroad.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Message {
    COMMON_RESPONSE_OK(
            HttpStatus.OK,
            "COMMON-RES001",
            "요청이 성공적으로 수행되었습니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String Message;
}
