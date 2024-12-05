package org.example.inpost.common;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class ErrorResponse {

    LocalDateTime timestamp;
    ErrorDetailsDto details;

    @Value
    @Builder
    @Jacksonized
    public static class ErrorDetailsDto {

        UUID id = UUID.randomUUID();
        int status;
        String code;
        String message;
    }

    public static ErrorResponse from(int status, String code, Exception ex) {
        final var details = new ErrorDetailsDto(status, code, ex.getMessage());
        return new ErrorResponse(LocalDateTime.now(), details);
    }
}
