package com.jejuroad.controller;

import com.jejuroad.common.HttpResponseBody;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import com.jejuroad.service.RestaurantService;
import com.jejuroad.service.CategoryService;
import com.jejuroad.service.TipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final CategoryService categoryService;
    private final TipService tipService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody RestaurantRequest.Register request) {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(restaurantService.register(request))
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

    @GetMapping("/categories")
    public ResponseEntity<Object> findCategory() {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(categoryService.find())
            .buildAndMapToResponseEntity();
    }

    @GetMapping("/tips")
    public ResponseEntity<Object> findTips() {
        return HttpResponseBody.builder()
            .message(COMMON_RESPONSE_OK)
            .information(tipService.find())
            .buildAndMapToResponseEntity();
    }

}
