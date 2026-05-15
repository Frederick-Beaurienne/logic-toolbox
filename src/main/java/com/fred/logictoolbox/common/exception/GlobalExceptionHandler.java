package com.fred.logictoolbox.common.exception;

import com.fred.logictoolbox.common.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for REST API errors.
 * <p>
 * This class centralizes exception handling in order to provide
 * standardized API error responses across the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ---------- VALIDATION EXCEPTIONS ---------- //

    /**
     * Handles validation constraint violations.
     *
     * @param exception the thrown constraint violation exception
     * @return a standardized bad request response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolationException(
            ConstraintViolationException exception
    ) {

        String message = exception.getConstraintViolations()
                .stream()
                .findFirst()
                .map(violation -> violation.getMessage())
                .orElse("Erreur de validation");

        ApiResponse<Void> response = ApiResponse.error(message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // ---------- REQUEST PARAMETER EXCEPTIONS ---------- //

    /**
     * Handles missing request parameters.
     *
     * @param exception the thrown exception
     * @return a standardized bad request response
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception
    ) {

        String message = String.format(
                "Le paramètre '%s' est obligatoire",
                exception.getParameterName()
        );

        ApiResponse<Void> response = ApiResponse.error(message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // ---------- GENERIC EXCEPTIONS ---------- //

    /**
     * Handles unexpected exceptions.
     *
     * @param exception the thrown exception
     * @return a standardized internal server error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception exception
    ) {

        ApiResponse<Void> response = ApiResponse.error(
                "Une erreur interne est survenue"
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}