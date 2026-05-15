package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * Request body for key search operations.
 */
public class FindKeysByValueRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotNull(message = "L'objet ne peut pas être null")
    private Map<String, Object> object;

    private Object searchedValue;

    // ---------- GETTERS & SETTERS ---------- //

    public Map<String, Object> getObject() {
        return object;
    }

    public void setObject(
            Map<String, Object> object
    ) {
        this.object = object;
    }

    public Object getSearchedValue() {
        return searchedValue;
    }

    public void setSearchedValue(
            Object searchedValue
    ) {
        this.searchedValue = searchedValue;
    }
}