package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Request body for grouping operations.
 */
public class GroupByPropertyRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotNull(message = "La liste des objets ne peut pas être null")
    @NotEmpty(message = "La liste des objets ne peut pas être vide")
    private List<Map<String, Object>> objects;

    @NotBlank(message = "La propriété de regroupement ne peut pas être vide")
    private String property;

    // ---------- GETTERS & SETTERS ---------- //

    public List<Map<String, Object>> getObjects() {
        return objects;
    }

    public void setObjects(
            List<Map<String, Object>> objects
    ) {
        this.objects = objects;
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