package com.my_money.models;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MutualFundInvestment {
    private MutualFundType type;
    private Double amount;
    private Double sipAmount;

    private List<Balance> balanceSheet = new ArrayList<>();

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

    public void applyRateChange(Month month, Float equityRateChange) {
        if (month == Month.JANUARY) {
            double balance = amount + (amount * equityRateChange / 100);
            balanceSheet.add(new Balance(month, balance));
        } else {
            Balance balance1 = balanceSheet.stream().filter(balance -> balance.getMonth() == Month.of(month.getValue() - 1))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cannot apply rate change as previous month's rate change not defined"));

            double balanceAndSipAmount = balance1.getAmount() + sipAmount;

            double balance = balanceAndSipAmount + (balanceAndSipAmount * equityRateChange / 100);
            balanceSheet.add(new Balance(month, balance));
        }
    }

    public Double getBalanceFor(Month month) {
        Optional<Balance> balance1 = balanceSheet.stream().filter(balance -> balance.getMonth() == month).findFirst();
        if (balance1.isPresent()) {
            return balance1.get().getAmount();
        } else {
            return null;
        }
    }

    public void setBalanceFor(String month, double amount) {
        balanceSheet.stream().filter(balance -> balance.getMonth() == Month.valueOf(month)).findFirst()
                .ifPresent(balance -> balance.setAmount(amount));
    }
}
