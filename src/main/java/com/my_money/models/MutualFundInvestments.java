package com.my_money.models;

import java.time.Month;
import java.util.ArrayList;

public class MutualFundInvestments extends ArrayList<MutualFundInvestment> {
    public MutualFundInvestments(Double equity, Double debt, Double gold) {
        MutualFundInvestment equityInvestment = new MutualFundInvestment(MutualFundType.EQUITY, equity);
        MutualFundInvestment debtInvestment = new MutualFundInvestment(MutualFundType.DEBT, debt);
        MutualFundInvestment goldInvestment = new MutualFundInvestment(MutualFundType.GOLD, gold);
        this.add(equityInvestment);
        this.add(debtInvestment);
        this.add(goldInvestment);
    }

    public void setSipAmounts(Double equity, Double debt, Double gold) {
        this.equityInvestment().setSipAmount(equity);
        this.debtInvestment().setSipAmount(debt);
        this.goldInvestment().setSipAmount(gold);
    }

    private MutualFundInvestment equityInvestment(){
        return this.get(0);
    }

    private MutualFundInvestment debtInvestment(){
        return this.get(1);
    }

    private MutualFundInvestment goldInvestment(){
        return this.get(2);
    }

    public void applyRateChange(Month month, Float equityRateChange, Float debtRateChange, Float goldRateChange) {
        equityInvestment().applyRateChange(month, equityRateChange);
        debtInvestment().applyRateChange(month, debtRateChange);
        goldInvestment().applyRateChange(month, goldRateChange);

    }

    public Double[] getBalanceFor(Month month) {
        Double[] result = new Double[3];
        result[0] = equityInvestment().getBalanceFor(month);
        result[1] = debtInvestment().getBalanceFor(month);
        result[2] = goldInvestment().getBalanceFor(month);
        return result;
    }

    public void rebalance(String month, double equityRebalanced, double debtRebalanced, double goldRebalanced) {
        equityInvestment().setBalanceFor(month, equityRebalanced);
        debtInvestment().setBalanceFor(month, debtRebalanced);
        goldInvestment().setBalanceFor(month, goldRebalanced);
    }
}
