package com.jejuroad.domain.restaurant;

import com.jejuroad.common.BusinessException;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.repository.CategoryRepository;
import com.jejuroad.repository.TipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_NONE_CATEGORY;
import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_NONE_TIP;

@Service
@AllArgsConstructor
public class RestaurantFactory {

    private TipRepository tipRepository;
    private CategoryRepository categoryRepository;

    public Restaurant createRestaurant(final RestaurantRequest.Register requestDto) {
        final List<Category> categories = getCategories(requestDto.getCategories());
        final List<Tip> tips = getTips(requestDto.getTipIds());

        return new Restaurant(
            requestDto.getName(),
            categories,
            requestDto.getIntroduction(),
            requestDto.getWayToGo(),
            new Address(
                requestDto.getZipcode(),
                requestDto.getState(),
                requestDto.getCity(),
                requestDto.getSimpleAddress(),
                requestDto.getDetailAddress(),
                requestDto.getLatitude(),
                requestDto.getLongitude()
            ),
            tips,
            createOpenTimes(requestDto.getOpenTimes()),
            requestDto.getImages()
        );
    }

    private List<Category> getCategories(final List<String> categoriesDto) {
        return categoriesDto
            .stream()
            .map(category -> categoryRepository.findByName(category).orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NONE_CATEGORY)))
            .collect(Collectors.toList());
    }

    private List<Tip> getTips(final List<Long> tipIdsDto) {
        return tipIdsDto
            .stream()
            .map(id -> tipRepository.findById(id).orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NONE_TIP)))
            .collect(Collectors.toList());
    }

    private List<OpenTime> createOpenTimes(final List<RestaurantRequest.Register.OpenTime> openTimesDto) {
        return openTimesDto
            .stream()
            .map(dto -> new OpenTime(
                OpenTime.Day.valueOf(dto.getDay().toUpperCase()),
                dto.getOperationStart(),
                dto.getOperationEnd(),
                dto.getBreakStart(),
                dto.getBreakEnd()
            ))
            .collect(Collectors.toList());
    }

}
