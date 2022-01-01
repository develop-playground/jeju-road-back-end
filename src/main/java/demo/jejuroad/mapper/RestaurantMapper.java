package demo.jejuroad.mapper;

import demo.jejuroad.domain.Restaurant;
import demo.jejuroad.dto.RestaurantRequest;
import demo.jejuroad.dto.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface RestaurantMapper {

    @Mapping(target = "address", source = "address.state")
    RestaurantResponse.Find mapToFindFrom(Restaurant restaurant);

    @Mapping(target = "address.zipcode", source = "zipcode")
    @Mapping(target = "address.state", source = "state")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.address", source = "address")
    @Mapping(target = "address.latitude", source = "latitude")
    @Mapping(target = "address.longitude", source = "longitude")
    Restaurant mapToRestaurantFrom(RestaurantRequest.Register register);

}
