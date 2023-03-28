package com.my_money.models;

public class AllocationPercentage {
    private float percentage;

    private MutualFundType type;

    public AllocationPercentage(MutualFundType type, double percentage) {
        this.percentage = (float) percentage;
        this.type = type;
    }

    public float getPercentage() {
        return percentage;
    }
}
