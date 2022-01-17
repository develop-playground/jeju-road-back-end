package com.jejuroad.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseBody<T> {

    private String code;
    private String message;
    private T information;

    public static <T> HttpResponseBody.HttpResponseBodyBuilder<T> builder() {
        return new HttpResponseBody.HttpResponseBodyBuilder();
    }

    public static class HttpResponseBodyBuilder<T> {
        private Message message;
        private T information;

        HttpResponseBodyBuilder() {
        }

        public HttpResponseBody.HttpResponseBodyBuilder<T> message(final Message message) {
            this.message = message;
            return this;
        }

        public HttpResponseBody.HttpResponseBodyBuilder<T> information(final T information) {
            this.information = information;
            return this;
        }

        public ResponseEntity<Object> buildAndMapToResponseEntity() {
            return ResponseEntity
                .status(message.getHttpStatus())
                .body(new HttpResponseBody(this.message.getCode(), this.message.getMessage(), this.information));
        }

    }

}
