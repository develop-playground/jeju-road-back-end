package demo.jejuroadbackend.controller;

import demo.jejuroadbackend.dto.RestaurantFind;
import demo.jejuroadbackend.dto.RestaurantRegister;
import demo.jejuroadbackend.service.RestaurantService;
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
