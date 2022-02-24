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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        final Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantResponse.Register.builder()
            .id(savedRestaurant.getId())
            .build();
    }

    public Page<RestaurantResponse.Find> find(Pageable pageable) {
        return restaurantRepository
            .findAll(pageable)
            .map((domain) -> mapper.mapToFindFrom(domain));
    }

    public RestaurantResponse.FindWithDetail findById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NOT_FOUND));
        return mapper.mapToFindWithDetailFrom(restaurant);
    }

}
