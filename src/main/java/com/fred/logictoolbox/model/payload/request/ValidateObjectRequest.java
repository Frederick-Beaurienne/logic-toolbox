package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * Request body for object schema validation.
 */
public class ValidateObjectRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotNull(message = "L'objet à valider ne peut pas être null")
    private Map<String, Object> object;

    @NotNull(message = "Le schéma ne peut pas être null")
    private Map<String, String> schema;

    // ---------- GETTERS & SETTERS ---------- //

    public Map<String, Object> getObject() {
        return object;
    }

    public void setObject(
            Map<String, Object> object
    ) {
        this.object = object;
    }

    public Map<String, String> getSchema() {
        return schema;
    }

    public void setSchema(
            Map<String, String> schema
    ) {
        this.schema = schema;
    }
}