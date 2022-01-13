package com.jejuroad.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public interface RestaurantRequest {
    @Data
    @Builder
    class Register {
        private String name;
        private List<String> categories;
        private String introduction;
        private String wayToGo;
        private String zipcode;
        private String state;
        private String city;
        private String simpleAddress;
        private String detailAddress;
        private double latitude;
        private double longitude;

        public List<String> getCategories() {
            return categories.stream().map(String::toUpperCase).collect(Collectors.toList());
        }
    }
}


