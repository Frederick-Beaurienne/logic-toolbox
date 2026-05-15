package com.fred.logictoolbox.model.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Request body for object creation from key-value pairs.
 */
public class CreateObjectFromPairsRequest {

    // ---------- ATTRIBUTES ---------- //

    @NotNull(message = "La liste des paires ne peut pas être null")
    @NotEmpty(message = "La liste des paires ne peut pas être vide")
    private List<List<Object>> pairs;

    // ---------- GETTERS & SETTERS ---------- //

    public List<List<Object>> getPairs() {
        return pairs;
    }

    public void setPairs(
            List<List<Object>> pairs
    ) {
        this.pairs = pairs;
    }
}