package com.jejuroad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


public interface RestaurantResponse {

    @Data
    @Builder
    class Register {
        private Long id;
    }

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