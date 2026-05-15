package com.fred.logictoolbox.model.payload.response.objectStatsResponse;

/**
 * Represents a complete statistical summary.
 */
public class ObjectStatsResponse {

    // ---------- ATTRIBUTES ---------- //

    private BasicStatsResponse basic;

    private AdvancedStatsResponse advanced;

    // ---------- GETTERS & SETTERS ---------- //

    public BasicStatsResponse getBasic() {
        return basic;
    }

    public void setBasic(
            BasicStatsResponse basic
    ) {
        this.basic = basic;
    }

    public AdvancedStatsResponse getAdvanced() {
        return advanced;
    }

    public void setAdvanced(
            AdvancedStatsResponse advanced
    ) {
        this.advanced = advanced;
    }
}