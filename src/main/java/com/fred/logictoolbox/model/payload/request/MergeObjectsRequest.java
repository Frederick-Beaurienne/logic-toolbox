package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * Request body for object merge operations.
 */
public class MergeObjectsRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotNull(message = "Le premier objet ne peut pas être null")
    private Map<String, Number> firstObject;

    @NotNull(message = "Le second objet ne peut pas être null")
    private Map<String, Number> secondObject;

    // ---------- GETTERS & SETTERS ---------- //

    public Map<String, Number> getFirstObject() {
        return firstObject;
    }

    public void setFirstObject(
            Map<String, Number> firstObject
    ) {
        this.firstObject = firstObject;
    }

    public Map<String, Number> getSecondObject() {
        return secondObject;
    }

    public void setSecondObject(
            Map<String, Number> secondObject
    ) {
        this.secondObject = secondObject;
    }
}