package demo.jejuroadbackend.controller;

import demo.jejuroadbackend.model.entity.Restaurant;
import demo.jejuroadbackend.model.network.request.RestaurantApiRequest;
import demo.jejuroadbackend.model.network.response.RestaurantApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantApiController extends BaseController<RestaurantApiRequest, RestaurantApiResponse, Restaurant>{
}
