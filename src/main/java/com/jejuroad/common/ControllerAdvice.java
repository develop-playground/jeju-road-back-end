package com.jejuroad.common;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public HttpEntity<Object> businessExceptionHandler(BusinessException e) {
        return HttpResponseBody.builder()
            .message(e.getBusinessMessage())
            .buildAndMapToResponseEntity();
    }

}
