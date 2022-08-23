package com.jejuroad.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jejuroad.domain.restaurant.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


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
    @AllArgsConstructor
    class Update {
        private Long id;
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
        private List<RestaurantResponse.Update.OpenTime> openTimes;

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

        public static RestaurantResponse.Update from(Restaurant restaurant) {
            Address address = restaurant.getAddress();
            List<Tip> tips = restaurant.getTips();
            List<com.jejuroad.domain.restaurant.OpenTime> openTimes = restaurant.getOpenTimes();

            return RestaurantResponse.Update.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .categories(getCategories(restaurant.getCategories()))
                .introduction(restaurant.getIntroduction())
                .wayToGo(restaurant.getWayToGo())
                .zipcode(address.getZipcode())
                .state(address.getState())
                .city(address.getCity())
                .simpleAddress(address.getSimple())
                .detailAddress(address.getDetail())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .tipIds(getTipIds(tips))
                .images(restaurant.getImages())
                .openTimes(createOpenTimes(openTimes))
                .build();
        }

        private static List<String> getCategories(final List<Category> categories) {
            return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
        }

        private static List<Long> getTipIds(final List<Tip> tips) {
            return tips.stream()
                .map(Tip::getId)
                .collect(Collectors.toList());
        }

        private static List<RestaurantResponse.Update.OpenTime> createOpenTimes(final List<com.jejuroad.domain.restaurant.OpenTime> openTimes) {
            return openTimes.stream()
                .map(openTime -> new RestaurantResponse.Update.OpenTime(
                    openTime.getDay().toString(),
                    openTime.getOperationStart(),
                    openTime.getOperationEnd(),
                    openTime.getBreakStart(),
                    openTime.getBreakEnd()
                ))
                .collect(Collectors.toList());
        }
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
    class DeleteMenu {
        private Long id;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class UpdateMenu {
        private Long id;
        private String name;
        private String image;
        private Integer price;
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
