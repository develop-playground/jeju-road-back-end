package com.jejuroad.controller;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jejuroad.common.Message.COMMON_RESPONSE_OK;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody RestaurantRequest.Register request) {
        return HttpResponseBody.builder()
                .message(COMMON_RESPONSE_OK)
                .information(restaurantService.register(request))
                .buildAndMapToResponseEntity();
    }

    @GetMapping
    public ResponseEntity<Object> find() {
        return HttpResponseBody.builder()
                .message(COMMON_RESPONSE_OK)
                .information(restaurantService.find())
                .buildAndMapToResponseEntity();
    }

}
