package org.example;

public class MutualFundInvestment {
    private MutualFundType type;
    private Double amount;
    private Double sipAmount;

    public MutualFundInvestment(MutualFundType type, Double amount) {
        this.type = type;
        this.amount = amount;
    }

    public MutualFundType getType() {
        return type;
    }

    public void setSipAmount(Double sipAmount) {
        this.sipAmount = sipAmount;
    }
}
