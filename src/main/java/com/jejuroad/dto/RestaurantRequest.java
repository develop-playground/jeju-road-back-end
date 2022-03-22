package com.jejuroad.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
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
        private List<Long> tipIds;
        private List<String> images;
        private List<OpenTime> openTimes;

        public List<String> getCategories() {
            return categories.stream().map(String::toUpperCase).collect(Collectors.toList());
        }

        @Data
        @AllArgsConstructor
        public static class OpenTime {
            private String day;
            @JsonFormat(pattern = "kk:mm:ss")
            private LocalTime operationStart;
            @JsonFormat(pattern = "kk:mm:ss")
            private LocalTime operationEnd;
            @JsonFormat(pattern = "kk:mm:ss")
            private LocalTime breakStart;
            @JsonFormat(pattern = "kk:mm:ss")
            private LocalTime breakEnd;
        }
    }

    @Data
    @Builder
    class RegisterMenu {
        private String name;
        private String image;
        private Integer price;
    }

}


