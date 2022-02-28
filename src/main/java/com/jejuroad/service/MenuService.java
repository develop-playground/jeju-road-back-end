package com.jejuroad.service;

import com.jejuroad.common.BusinessException;
import com.jejuroad.common.mapper.MenuMapper;
import com.jejuroad.domain.Menu;
import com.jejuroad.domain.Restaurant;
import com.jejuroad.dto.MenuRequest;
import com.jejuroad.dto.MenuResponse;
import com.jejuroad.repository.MenuRepository;
import com.jejuroad.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_NOT_FOUND;

@Service
@AllArgsConstructor
public class MenuService {

    private MenuRepository menuRepository;
    private RestaurantRepository restaurantRepository;
    private MenuMapper mapper;

    public MenuResponse.Register register(final MenuRequest.Register request) {
        final Menu menu = mapper.mapToMenuFrom(request);
        System.out.println(menu.getName());

        final Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
            .orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NOT_FOUND));
        menu.setRestaurant(restaurant);
        final Menu savedMenu = menuRepository.save(menu);

        return MenuResponse.Register.builder()
            .id(savedMenu.getId())
            .build();
    }
}
