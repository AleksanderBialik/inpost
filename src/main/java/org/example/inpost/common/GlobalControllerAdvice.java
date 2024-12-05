package org.example.inpost.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException ex) {
        return getResponse(CONFLICT, ex);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return getResponse(NOT_FOUND, ex);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return getResponse(BAD_REQUEST, ex);
    }

    private ResponseEntity<ErrorResponse> getResponse(HttpStatus status, Exception exception) {
        ErrorResponse response = ErrorResponse.from(status.value(), status.name(), exception);
        logError(response.getDetails().getId(), exception);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        return getResponse(INTERNAL_SERVER_ERROR, ex);
    }

    private void logError(UUID errorId, Exception ex) {
        log.error(MessageFormat.format("Exception id {0} - {1}", errorId, ex.getMessage()), ex);
    }

}
