package com.jejuroad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface TipResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class Find {
        Long id;
        String content;
    }

}
