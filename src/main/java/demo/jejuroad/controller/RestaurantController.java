package demo.jejuroad.controller;

import demo.jejuroad.dto.RestaurantRequest;
import demo.jejuroad.dto.RestaurantResponse;
import demo.jejuroad.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private RestaurantService restaurantService;

    @PostMapping
    public Long register(@RequestBody RestaurantRequest.Register request) {
        return restaurantService.register(request);
    }

    @GetMapping
    public List<RestaurantResponse.Find> find() {
        return restaurantService.find();
    }

}
