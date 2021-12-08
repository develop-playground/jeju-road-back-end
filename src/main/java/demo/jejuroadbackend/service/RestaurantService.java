package demo.jejuroadbackend.service;

import demo.jejuroadbackend.domain.Restaurant;
import demo.jejuroadbackend.dto.RestaurantFind;
import demo.jejuroadbackend.dto.RestaurantRegister;
import demo.jejuroadbackend.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository repository;

    public Long register(final RestaurantRegister request) {
        Restaurant restaurant = Restaurant.of(request);
        return repository.save(restaurant).getId();
    }

    public List<RestaurantFind> find() {
        return repository.findAll().stream().map(RestaurantFind::of).collect(Collectors.toList());
    }

}
