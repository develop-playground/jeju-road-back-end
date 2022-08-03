package com.jejuroad.controller;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import com.jejuroad.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{restaurantId}/menus")
    public ResponseEntity<Object> registerMenu(@PathVariable Long restaurantId, @RequestBody RestaurantRequest.RegisterMenu request) {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(restaurantService.registerMenu(restaurantId, request))
            .buildAndMapToResponseEntity();
    }

    @GetMapping
    public ResponseEntity<Object> find(Pageable pageable, PagedResourcesAssembler<RestaurantResponse.Find> assembler) {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(assembler.toModel(restaurantService.find(pageable)))
            .buildAndMapToResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(restaurantService.findById(id))
            .buildAndMapToResponseEntity();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody RestaurantRequest.Register request) {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(restaurantService.update(id,request))
            .buildAndMapToResponseEntity();
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> findCategories() {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(restaurantService.findCategories())
            .buildAndMapToResponseEntity();
    }

    @GetMapping("/tips")
    public ResponseEntity<Object> findTips() {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(restaurantService.findTips())
            .buildAndMapToResponseEntity();
    }

}
