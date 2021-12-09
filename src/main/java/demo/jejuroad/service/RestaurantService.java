package demo.jejuroad.service;

import demo.jejuroad.domain.Restaurant;
import demo.jejuroad.dto.RestaurantFind;
import demo.jejuroad.dto.RestaurantRegister;
import demo.jejuroad.mapper.RestaurantMapper;
import demo.jejuroad.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository repository;
    private RestaurantMapper mapper;

    public Long register(final RestaurantRegister request) {
        Restaurant restaurant = mapper.getRestaurantFromRegister(request);
        return repository.save(restaurant).getId();
    }

    public List<RestaurantFind> find() {
        return repository
            .findAll()
            .stream()
            .map((domain) -> mapper.getFindFromRestaurant(domain))
            .collect(Collectors.toList());
    }

}
