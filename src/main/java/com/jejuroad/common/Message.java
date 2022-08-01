package com.jejuroad.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum Message {
    COMMON_RESPONSE_OK(
        HttpStatus.OK,
        "COMMON-RES001",
        "요청이 성공적으로 수행되었습니다."
    ),
    RESTAURANT_RESPONSE_NONE_CATEGORY(
        BAD_REQUEST,
        "RESTAURANT-RES001",
        "존재하지 않는 맛집 카테고리입니다."
    ),
    RESTAURANT_RESPONSE_NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "RESTAURANT-RES002",
            "식별자에 해당하는 맛집이 존재하지 않습니다."
    ),
    RESTAURANT_RESPONSE_NONE_TIP(
        BAD_REQUEST,
        "RESTAURANT-RES003",
        "존재하지 않는 이용팁입니다."
    ),
    RESTAURANT_RESPONSE_DUPLICATED_MENU_NAME(
        BAD_REQUEST,
        "RESTAURANT-RES004",
        "이미 같은 이름의 메뉴가 해당 맛집에 존재합니다."
    ),
    RESTAURANT_RESPONSE_MENU_NOT_FOUND_IN_RESTAURANT(
        BAD_REQUEST,
        "RESTAURANT-RES005",
        "식별자에 해당하는 메뉴가 맛집에 존재하지 않습니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
