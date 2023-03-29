package com.my_money.models;

import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

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

    public Optional<Float> getRateChangeFor(Month month, MutualFundType type) {
        Optional<Float> result = Optional.ofNullable(null);
        switch (type) {
            case EQUITY:
                result = equityMutualFund().getRateChange(month);
                break;
            case DEBT:
                result = debtMutualFund().getRateChange(month);
                break;
            case GOLD:
                result = goldMutualFund().getRateChange(month);
                break;
        }
        return result;
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
