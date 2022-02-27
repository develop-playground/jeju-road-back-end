package com.jejuroad.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface CategoryResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class Find {
        String name;
    }

}
