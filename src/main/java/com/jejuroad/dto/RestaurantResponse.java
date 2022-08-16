package com.jejuroad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public interface RestaurantResponse {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Register {
        private Long id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Delete {
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
        private List<Menu> menus;
        private String wayToGo;
        private String simpleAddress;
        private String detailAddress;
        private List<OpenTime> openTimes;
        private String introduction;
        private List<String> tips;
        private List<String> images;

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
            private String servingTime;
            private String breakTime;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class RegisterMenu {
        private Long id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class FindCategory {
        String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class FindTip {
        Long id;
        String content;
    }

}
