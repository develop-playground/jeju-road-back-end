package com.jejuroad.service;

import com.jejuroad.common.BusinessException;
import com.jejuroad.common.mapper.RestaurantMapper;
import com.jejuroad.domain.Restaurant;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import com.jejuroad.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_NOT_FOUND;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private RestaurantMapper mapper;

    public RestaurantResponse.Register register(final RestaurantRequest.Register request) {
        final Restaurant restaurant = mapper.mapToRestaurantFrom(request);
        final Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantResponse.Register.builder()
            .id(savedRestaurant.getId())
            .build();
    }

    public List<RestaurantResponse.Find> find() {
        return restaurantRepository
            .findAll()
            .stream()
            .map((domain) -> mapper.mapToFindFrom(domain))
            .collect(Collectors.toList());
    }

    public RestaurantResponse.FindWithDetail findById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NOT_FOUND));
        System.out.println(restaurant);
        return mapper.mapToFindWithDetailFrom(restaurant);
    }

}
