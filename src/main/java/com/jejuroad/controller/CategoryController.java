package com.jejuroad.controller;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jejuroad.common.Message.COMMON_RESPONSE_OK;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> find() {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(categoryService.find())
            .buildAndMapToResponseEntity();
    }

}
