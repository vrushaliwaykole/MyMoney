package com.my_money.models;

import java.time.Month;
import java.util.ArrayList;

public class MutualFunds extends ArrayList<MutualFund> {
    public MutualFunds() {
        super(3);
        this.add(new MutualFund(MutualFundType.EQUITY));
        this.add(new MutualFund(MutualFundType.DEBT));
        this.add(new MutualFund(MutualFundType.GOLD));
    }

    public void addRateChange(String month, String equityRateChange, String debtRateChange, String goldRateChange) {
        equityMutualFund().addRateChange(month, Float.parseFloat(equityRateChange));
        debtMutualFund().addRateChange(month, Float.parseFloat(debtRateChange));
        goldMutualFund().addRateChange(month, Float.parseFloat(goldRateChange));
    }

    public Float getRateChangeFor(Month month, MutualFundType type) {
        return switch (type) {
            case EQUITY -> equityMutualFund().getRateChange(month).orElseThrow(() -> new RuntimeException("Cannot get rate change for " + month));
            case DEBT -> debtMutualFund().getRateChange(month).orElseThrow(() -> new RuntimeException("Cannot get rate change for " + month));
            case GOLD -> goldMutualFund().getRateChange(month).orElseThrow(() -> new RuntimeException("Cannot get rate change for " + month));
        };
    }

    private MutualFund equityMutualFund(){
        return this.get(0);
    }

    private MutualFund debtMutualFund(){
        return this.get(1);
    }

    private MutualFund goldMutualFund(){
        return this.get(2);
    }
}
