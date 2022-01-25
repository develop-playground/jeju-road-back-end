package com.jejuroad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class FindWithDetail {
        private Long id;
        private String name;
        private List<String> images = new ArrayList<>();
        private List<Menu> menus;
        @JsonProperty("way_to_go")
        private String wayToGo;
        @JsonProperty("simple_address")
        private String simpleAddress;
        @JsonProperty("detail_address")
        private String detailAddress;
        @JsonProperty("open_times")
        private List<OpenTime> openTimes;
        private String introduction;
        private List<String> tips;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Menu {
            private Long id;
            private String name;
            private int price;
            private String image = "임시";
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class OpenTime {
            private String day;
            @JsonProperty("serving_time")
            private String servingTime;
            @JsonProperty("break_time")
            private String breakTime;
        }
    }

}