package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Request body for property extraction operations.
 */
public class ExtractPropertiesRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotNull(message = "L'objet ne peut pas être null")
    private Map<String, Object> object;

    @NotNull(message = "La liste des propriétés ne peut pas être null")
    @NotEmpty(message = "La liste des propriétés ne peut pas être vide")
    private List<String> properties;

    // ---------- GETTERS & SETTERS ---------- //

    public Map<String, Object> getObject() {
        return object;
    }

    public void setObject(
            Map<String, Object> object
    ) {
        this.object = object;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(
            List<String> properties
    ) {
        this.properties = properties;
    }
}