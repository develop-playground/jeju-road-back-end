package demo.jejuroad.service;

import demo.jejuroad.domain.Restaurant;
import demo.jejuroad.dto.RestaurantRequest;
import demo.jejuroad.dto.RestaurantResponse;
import demo.jejuroad.mapper.RestaurantMapper;
import demo.jejuroad.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private RestaurantMapper mapper;

    public Long register(final RestaurantRequest.Register request) {
        Restaurant restaurant = mapper.mapToRestaurantFrom(request);
        return restaurantRepository.save(restaurant).getId();
    }

    public List<RestaurantResponse.Find> find() {
        return restaurantRepository
            .findAll()
            .stream()
            .map((domain) -> mapper.mapToFindFrom(domain))
            .collect(Collectors.toList());
    }

}
