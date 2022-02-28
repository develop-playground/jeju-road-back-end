package com.jejuroad.controller;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.dto.MenuRequest;
import com.jejuroad.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jejuroad.common.Message.COMMON_RESPONSE_OK;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody MenuRequest.Register request) {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(menuService.register(request))
            .buildAndMapToResponseEntity();
    }

}
