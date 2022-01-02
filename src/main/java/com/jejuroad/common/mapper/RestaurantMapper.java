package com.jejuroad.common.mapper;

import com.jejuroad.domain.Restaurant;
import com.jejuroad.dto.RestaurantRequest;
import com.jejuroad.dto.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

}
