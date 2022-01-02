package com.jejuroad.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public interface RestaurantRequest {
    @Data
    class Register {
        private String name;
        private List<String> categories;
        private String introduction;
        private String wayToGo;
        private String zipcode;
        private String state;
        private String city;
        private String address;
        private double latitude;
        private double longitude;

        public List<String> getCategories() {
            return categories.stream().map(String::toUpperCase).collect(Collectors.toList());
        }
    }
}


