package demo.jejuroad.controller;

import demo.jejuroad.dto.RestaurantFind;
import demo.jejuroad.dto.RestaurantRegister;
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
    public Long register(@RequestBody RestaurantRegister request) {
        return restaurantService.register(request);
    }

    @GetMapping
    public List<RestaurantFind> find() {
        return restaurantService.find();
    }

}
