package com.jejuroad.domain.restaurant;

import com.jejuroad.dto.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "address", source = "address.simple")
    @Mapping(target = "image", source = "images")
    RestaurantResponse.Find mapToFindFrom(Restaurant restaurant);

    default String mapToImageFrom(List<String> images) {
        return images.get(0);
    }

    @Mapping(target = "simpleAddress", source = "address.simple")
    @Mapping(target = "detailAddress", source = "address.detail")
    RestaurantResponse.FindWithDetail mapToFindWithDetailFrom(Restaurant restaurant);

    RestaurantResponse.FindWithDetail.Menu map(Menu menu);

    RestaurantResponse.FindTip mapToFindTipFrom(Tip tip);

    default List<String> map(List<Tip> tips) {
        return tips.stream().map(Tip::getContent).collect(Collectors.toList());
    }

    default RestaurantResponse.FindWithDetail.OpenTime map(OpenTime openTime) {
        return new RestaurantResponse.FindWithDetail.OpenTime(
            openTime.getDay().name(),
            openTime.getOperationStart().toString() + '-' + openTime.getOperationEnd().toString(),
            openTime.getBreakStart().toString() + '-' + openTime.getBreakEnd().toString()
        );
    }

    default String mapToStringFrom(Category category) {
        return category.getName();
    }

    default RestaurantResponse.FindCategory mapToFindCategoryFrom(Category category) {
        return new RestaurantResponse.FindCategory(category.getName());
    }

}
