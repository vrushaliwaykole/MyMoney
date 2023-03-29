package com.my_money.models;

import java.time.Month;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Portfolio {
    private final MutualFundInvestments investments;
    private final AllocationPercentage[] allocationPercentage = new AllocationPercentage[3];

    public Portfolio(Double equity, Double debt, Double gold) {
        this.investments = new MutualFundInvestments(equity, debt, gold);
        double total = equity + debt + gold;
        this.allocationPercentage[0] = new AllocationPercentage(MutualFundType.EQUITY, equity / total * 100);
        this.allocationPercentage[1] = new AllocationPercentage(MutualFundType.DEBT, debt / total * 100);
        this.allocationPercentage[2] = new AllocationPercentage(MutualFundType.GOLD, gold / total * 100);
    }

    public void sip(Double equity, Double debt, Double gold) {
        this.investments.setSipAmounts(equity, debt, gold);
    }

    public void applyRateChange(String month, MutualFunds mutualFunds) {
        Optional<Float> equityRateChange = mutualFunds.getRateChangeFor(Month.valueOf(month), MutualFundType.EQUITY);
        Optional<Float> debtRateChange = mutualFunds.getRateChangeFor(Month.valueOf(month), MutualFundType.DEBT);
        Optional<Float> goldRateChange = mutualFunds.getRateChangeFor(Month.valueOf(month), MutualFundType.GOLD);

        if(!equityRateChange.isPresent() || !debtRateChange.isPresent() || !goldRateChange.isPresent()) {
            throw new RuntimeException("Cannot get rate change for " + month);
        }

        this.investments.applyRateChange(Month.valueOf(month), equityRateChange.get(), debtRateChange.get(), goldRateChange.get());

        if(Month.valueOf(month) == Month.JUNE || Month.valueOf(month) == Month.DECEMBER) {
            this.rebalance(month);
        }
    }

    public Double[] getBalanceFor(String month) {
        return investments.getBalanceFor(Month.valueOf(month));
    }

    public void rebalance(String month) {
        Double[] balances = getBalanceFor(month);
        double total = balances[0] + balances[1] + balances[2];

        double equityRebalanced = Math.floor(allocationPercentage[0].getPercentage() * total / 100);
        double debtRebalanced = Math.floor(allocationPercentage[1].getPercentage() * total / 100);
        double goldRebalanced = Math.floor(allocationPercentage[2].getPercentage() * total / 100);

        this.investments.rebalance(month, equityRebalanced, debtRebalanced, goldRebalanced);
    }

    public Double[] getLastRebalanceAmounts() {
        Double[] result = investments.getBalanceFor(Month.DECEMBER);
        int rebalanceSize = (int) Arrays.stream(result).filter(Objects::nonNull).count();
        if(rebalanceSize != 3) {
            result = investments.getBalanceFor(Month.JUNE);
            rebalanceSize = (int) Arrays.stream(result).filter(Objects::nonNull).count();
            if(rebalanceSize != 3) {
                return new Double[0];
            }else{
                return result;
            }
        }else{
            return result;
        }
    }
}
