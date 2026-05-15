package com.fred.logictoolbox.model.payload.response.objectStatsResponse;

/**
 * Represents basic statistics.
 */
public class BasicStatsResponse {

    // ---------- ATTRIBUTES ---------- //

    private double min;

    private double max;

    private double average;

    private double total;

    // ---------- GETTERS & SETTERS ---------- //

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}