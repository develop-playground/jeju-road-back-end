package com.jejuroad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public interface RestaurantResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class Register {
        private Long id;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class Find {
        private Long id;
        private String name;
        private List<String> categories;
        private String address;
        private String image;
        private String introduction;
    }

}