package com.springframework.CareerConnect.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Optional: Builder pattern for more flexibility
    public static class Builder {
        private String message;
        private LocalDateTime timestamp;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponse build() {
            ErrorResponse response = new ErrorResponse(this.message);
            if (this.timestamp != null) {
                response.timestamp = this.timestamp;
            }
            return response;
        }
    }
}