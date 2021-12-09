package demo.jejuroad.dto;

import demo.jejuroad.domain.Restaurant;
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

}
