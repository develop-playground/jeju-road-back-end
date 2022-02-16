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
    ),
    RESTAURANT_RESPONSE_NONE_CATEGORY(
        HttpStatus.BAD_REQUEST,
        "RESTAURANT-RES001",
        "존재하지 않는 맛집 카테고리입니다."
    ),
    RESTAURANT_RESPONSE_NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "RESTAURANT-RES002",
            "식별자에 해당하는 맛집이 존재하지 않습니다."
    ),
    RESTAURANT_RESPONSE_NONE_TIP(
        HttpStatus.BAD_REQUEST,
        "RESTAURANT-RES003",
        "존재하지 않는 이용팁입니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
