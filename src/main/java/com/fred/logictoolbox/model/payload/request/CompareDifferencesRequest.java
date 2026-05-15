package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * Request body for object difference comparison operations.
 */
public class CompareDifferencesRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotNull(message = "Le premier objet ne peut pas être null")
    private Map<String, Object> firstObject;

    @NotNull(message = "Le second objet ne peut pas être null")
    private Map<String, Object> secondObject;

    // ---------- GETTERS & SETTERS ---------- //

    public Map<String, Object> getFirstObject() {
        return firstObject;
    }

    public void setFirstObject(
            Map<String, Object> firstObject
    ) {
        this.firstObject = firstObject;
    }

    public Map<String, Object> getSecondObject() {
        return secondObject;
    }

    public void setSecondObject(
            Map<String, Object> secondObject
    ) {
        this.secondObject = secondObject;
    }
}