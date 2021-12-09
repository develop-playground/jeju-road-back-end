package demo.jejuroad.mapper;

import demo.jejuroad.domain.Restaurant;
import demo.jejuroad.dto.RestaurantFind;
import demo.jejuroad.dto.RestaurantRegister;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(target = "address", source = "address.state")
    RestaurantFind getFindFromRestaurant(Restaurant restaurant);

    @Mapping(target = "address.zipcode", source = "zipcode")
    @Mapping(target = "address.state", source = "state")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.address", source = "address")
    @Mapping(target = "address.latitude", source = "latitude")
    @Mapping(target = "address.longitude", source = "longitude")
    Restaurant getRestaurantFromRegister(RestaurantRegister register);
}
