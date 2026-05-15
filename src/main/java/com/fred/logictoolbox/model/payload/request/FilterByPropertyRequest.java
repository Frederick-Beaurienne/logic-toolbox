package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Request body for filtering object arrays by property.
 */
public class FilterByPropertyRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotEmpty(message = "La liste d'objets ne peut pas être vide")
    private List<Map<String, Object>> objects;

    @NotBlank(message = "La propriété ne peut pas être vide")
    private String property;

    @NotNull(message = "La valeur recherchée ne peut pas être null")
    private Object value;

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

    public Object getValue() {
        return value;
    }

    public void setValue(
            Object value
    ) {
        this.value = value;
    }
}