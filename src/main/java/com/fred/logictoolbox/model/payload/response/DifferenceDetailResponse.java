package com.fred.logictoolbox.model.payload.response;

/**
 * Represents a detailed object difference.
 */
public class DifferenceDetailResponse {

    // ---------- ATTRIBUTES ---------- //

    private String type;

    private Object oldValue;

    private Object newValue;

    // ---------- CONSTRUCTORS ---------- //

    public DifferenceDetailResponse() {
    }

    public DifferenceDetailResponse(
            String type,
            Object oldValue,
            Object newValue
    ) {
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // ---------- GETTERS & SETTERS ---------- //

    public String getType() {
        return type;
    }

    public void setType(
            String type
    ) {
        this.type = type;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(
            Object oldValue
    ) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(
            Object newValue
    ) {
        this.newValue = newValue;
    }
}