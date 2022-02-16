package com.jejuroad.service;

import com.jejuroad.common.BusinessException;
import com.jejuroad.common.mapper.RestaurantMapper;
import com.jejuroad.domain.Restaurant;
import com.jejuroad.domain.Tip;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import com.jejuroad.repository.RestaurantRepository;
import com.jejuroad.repository.TipRepository;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_NONE_TIP;
import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_NOT_FOUND;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private TipRepository tipRepository;
    private RestaurantMapper mapper;

    public RestaurantResponse.Register register(final RestaurantRequest.Register request) {
        final Restaurant restaurant = mapper.mapToRestaurantFrom(request);
        final List<Tip> tips = request.getTipIds().stream()
            .map(id -> tipRepository.findById(id).orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NONE_TIP)))
            .collect(Collectors.toList());
        restaurant.setTips(tips);
        LoggerFactory.getLogger("AAA===").info(restaurant.toString());
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
