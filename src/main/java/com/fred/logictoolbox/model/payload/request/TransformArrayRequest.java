package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

/**
 * Request body for array transformation operations.
 */
public class TransformArrayRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotEmpty(message = "La liste d'objets ne peut pas être vide")
    private List<Map<String, Object>> objects;

    @NotBlank(message = "Le type de transformation ne peut pas être vide")
    private String transformation;

    // ---------- GETTERS & SETTERS ---------- //

    public List<Map<String, Object>> getObjects() {
        return objects;
    }

    public void setObjects(
            List<Map<String, Object>> objects
    ) {
        this.objects = objects;
    }

    public String getTransformation() {
        return transformation;
    }

    public void setTransformation(
            String transformation
    ) {
        this.transformation = transformation;
    }
}