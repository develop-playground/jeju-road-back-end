package demo.jejuroadbackend.dto;

import demo.jejuroadbackend.domain.Restaurant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantFind {

    private Long id;
    private String name;
    private String category;
    private String address;
    private String image;
    private String introduction;

    public static RestaurantFind of(Restaurant restaurant) {
        return builder()
            .id(restaurant.getId())
            .name(restaurant.getName())
            .category(restaurant.getCategory().name())
            .address(restaurant.getAddress().getState())
            .image("임시")
            .introduction(restaurant.getIntroduction())
            .build();
    }

}
