package com.fred.logictoolbox.model.payload.response.objectStatsResponse;

/**
 * Represents advanced statistics.
 */
public class AdvancedStatsResponse {

    // ---------- ATTRIBUTES ---------- //

    private double median;

    private double variance;

    private double standardDeviation;

    // ---------- GETTERS & SETTERS ---------- //

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(
            double standardDeviation
    ) {
        this.standardDeviation = standardDeviation;
    }
}