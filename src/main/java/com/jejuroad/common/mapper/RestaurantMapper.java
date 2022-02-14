package com.jejuroad.common.mapper;

import com.jejuroad.domain.Menu;
import com.jejuroad.domain.OpenTime;
import com.jejuroad.domain.Restaurant;
import com.jejuroad.domain.Tip;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface RestaurantMapper {

    @Mapping(target = "address", source = "address.simple")
    RestaurantResponse.Find mapToFindFrom(Restaurant restaurant);

    @Mapping(target = "address.zipcode", source = "zipcode")
    @Mapping(target = "address.state", source = "state")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.simple", source = "simpleAddress")
    @Mapping(target = "address.detail", source = "detailAddress")
    @Mapping(target = "address.latitude", source = "latitude")
    @Mapping(target = "address.longitude", source = "longitude")
    Restaurant mapToRestaurantFrom(RestaurantRequest.Register register);

    @Mapping(target = "simpleAddress", source = "address.simple")
    @Mapping(target = "detailAddress", source = "address.detail")
    RestaurantResponse.FindWithDetail mapToFindWithDetailFrom(Restaurant restaurant);

    RestaurantResponse.FindWithDetail.Menu map(Menu menu);

    default List<String> map(List<Tip> tips) {
        return tips.stream().map(Tip::getContent).collect(Collectors.toList());
    }

    default OpenTime map(RestaurantRequest.Register.OpenTime openTime) {
        return OpenTime.builder()
            .day(OpenTime.Day.valueOf(openTime.getDay().toUpperCase()))
            .operationStart(openTime.getOperationStart())
            .operationEnd(openTime.getOperationEnd())
            .breakStart(openTime.getBreakStart())
            .breakEnd(openTime.getBreakEnd())
            .build();
    }

    default RestaurantResponse.FindWithDetail.OpenTime map(OpenTime openTime) {
        return new RestaurantResponse.FindWithDetail.OpenTime(
            openTime.getDay().name(),
            openTime.getOperationStart().toString() + '-' + openTime.getOperationEnd().toString(),
            openTime.getBreakStart().toString() + '-' + openTime.getBreakEnd().toString()
        );
    }

}
