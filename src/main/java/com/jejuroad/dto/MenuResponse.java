package com.jejuroad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface MenuResponse {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Register {
        private Long id;
    }

}
