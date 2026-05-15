package com.fred.logictoolbox.common.exception;

import com.fred.logictoolbox.model.payload.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    // ---------- JSON PARSING EXCEPTIONS ---------- //

    /**
     * Handles invalid or unreadable JSON request bodies.
     *
     * @param exception the thrown exception
     * @return a standardized bad request response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception
    ) {

        ApiResponse<Void> response = ApiResponse.error(
                "Le format des données JSON est invalide"
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // ---------- BUSINESS LOGIC EXCEPTIONS ---------- //

    /**
     * Handles illegal argument exceptions.
     *
     * @param exception the thrown exception
     * @return a standardized bad request response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {

        ApiResponse<Void> response = ApiResponse.error(
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // ---------- VALIDATION EXCEPTIONS ---------- //

    /**
     * Handles bean validation errors on request bodies.
     *
     * @param exception the thrown exception
     * @return a standardized bad request response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {

        String errorMessage = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField()
                                + " : "
                                + error.getDefaultMessage()
                )
                .findFirst()
                .orElse("Requête invalide");

        ApiResponse<Void> response =
                ApiResponse.error(errorMessage);

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