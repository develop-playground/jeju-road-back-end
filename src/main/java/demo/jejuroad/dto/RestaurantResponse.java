package demo.jejuroad.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


public interface RestaurantResponse {

    @Data
    @Builder
    class Find {

        private Long id;
        private String name;
        private List<String> categories;
        private String address;
        private String image;
        private String introduction;

    }

}


