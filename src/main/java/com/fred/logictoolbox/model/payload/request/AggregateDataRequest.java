package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

/**
 * Request body for data aggregation operations.
 */
public class AggregateDataRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotEmpty(message = "La liste d'objets ne peut pas être vide")
    private List<Map<String, Object>> objects;

    @NotBlank(message = "La propriété de regroupement ne peut pas être vide")
    private String groupBy;

    @NotBlank(message = "La propriété numérique ne peut pas être vide")
    private String valueField;

    // ---------- GETTERS & SETTERS ---------- //

    public List<Map<String, Object>> getObjects() {
        return objects;
    }

    public void setObjects(
            List<Map<String, Object>> objects
    ) {
        this.objects = objects;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(
            String groupBy
    ) {
        this.groupBy = groupBy;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(
            String valueField
    ) {
        this.valueField = valueField;
    }
}