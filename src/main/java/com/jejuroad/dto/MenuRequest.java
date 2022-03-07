package com.jejuroad.dto;

import lombok.Builder;
import lombok.Data;

public interface MenuRequest {

    @Data
    @Builder
    class Register {
        private Long restaurantId;
        private String name;
        private String image;
        private Integer price;
    }

}
