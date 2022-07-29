package com.jejuroad.service;

import com.jejuroad.common.BusinessException;
import com.jejuroad.domain.restaurant.RestaurantMapper;
import com.jejuroad.domain.restaurant.Restaurant;
import com.jejuroad.domain.restaurant.RestaurantFactory;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import com.jejuroad.repository.CategoryRepository;
import com.jejuroad.repository.RestaurantRepository;
import com.jejuroad.repository.TipRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_NOT_FOUND;

@Service
@AllArgsConstructor
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private CategoryRepository categoryRepository;
    private TipRepository tipRepository;
    private RestaurantFactory factory;
    private RestaurantMapper mapper;

    public RestaurantResponse.Register register(final RestaurantRequest.Register request) {
        final Restaurant newRestaurant = factory.createRestaurant(request);
        final Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

        return new RestaurantResponse.Register(savedRestaurant.getId());
    }

    public RestaurantResponse.RegisterMenu registerMenu(final Long restaurantId, final RestaurantRequest.RegisterMenu request) {
        final Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NOT_FOUND));
        restaurant.addMenus(request);
        final Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        final Long savedMenuId = savedRestaurant.getMenus().stream().filter(menu -> menu.getName().equals(request.getName()))
            .collect(Collectors.toList()).get(0).getId();

        return new RestaurantResponse.RegisterMenu(savedMenuId);
    }

    public Page<RestaurantResponse.Find> find(Pageable pageable) {
        return restaurantRepository
            .findAll(pageable)
            .map((domain) -> mapper.mapToFindFrom(domain));
    }

    @Transactional
    public RestaurantResponse.Delete delete(final Long id) {
        final Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NOT_FOUND));
        restaurantRepository.delete(restaurant);

        return mapper.mapToDeleteFrom(restaurant);
    }

    public RestaurantResponse.FindWithDetail findById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NOT_FOUND));
        return mapper.mapToFindWithDetailFrom(restaurant);
    }

    public List<RestaurantResponse.FindCategory> findCategories() {
        return categoryRepository
            .findAll()
            .stream()
            .map((category) -> mapper.mapToFindCategoryFrom(category))
            .collect(Collectors.toList());
    }

    public List<RestaurantResponse.FindTip> findTips() {
        return tipRepository
            .findAll()
            .stream()
            .map((tip) -> mapper.mapToFindTipFrom(tip))
            .collect(Collectors.toList());
    }
}
