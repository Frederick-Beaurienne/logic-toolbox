package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

/**
 * Request body for array intersection operations.
 */
public class FindIntersectionRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotEmpty(message = "Le premier tableau ne peut pas être vide")
    private List<Map<String, Object>> firstArray;

    @NotEmpty(message = "Le second tableau ne peut pas être vide")
    private List<Map<String, Object>> secondArray;

    @NotBlank(message = "La propriété ne peut pas être vide")
    private String property;

    // ---------- GETTERS & SETTERS ---------- //

    public List<Map<String, Object>> getFirstArray() {
        return firstArray;
    }

    public void setFirstArray(
            List<Map<String, Object>> firstArray
    ) {
        this.firstArray = firstArray;
    }

    public List<Map<String, Object>> getSecondArray() {
        return secondArray;
    }

    public void setSecondArray(
            List<Map<String, Object>> secondArray
    ) {
        this.secondArray = secondArray;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(
            String property
    ) {
        this.property = property;
    }
}