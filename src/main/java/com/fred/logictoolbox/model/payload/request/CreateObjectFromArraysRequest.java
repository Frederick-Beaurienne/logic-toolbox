package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Request body for object creation from arrays.
 */
public class CreateObjectFromArraysRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotNull(message = "La liste des clés ne peut pas être null")
    @NotEmpty(message = "La liste des clés ne peut pas être vide")
    private List<String> keys;

    @NotNull(message = "La liste des valeurs ne peut pas être null")
    @NotEmpty(message = "La liste des valeurs ne peut pas être vide")
    private List<Object> values;

    // ---------- GETTERS & SETTERS ---------- //

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(
            List<String> keys
    ) {
        this.keys = keys;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(
            List<Object> values
    ) {
        this.values = values;
    }
}