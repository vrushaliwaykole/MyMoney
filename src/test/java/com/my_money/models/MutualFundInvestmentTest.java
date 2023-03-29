package com.my_money.models;

import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class MutualFundInvestmentTest {
    @Test
    void shouldThrowErrorIfSipAmountNotDefined() {
        MutualFundInvestment investment = new MutualFundInvestment(MutualFundType.EQUITY, 10000.0);

        assertThrows(IllegalStateException.class, () -> investment.applyRateChange(Month.JANUARY, new Float(10.0)), "Cannot apply rate change as sip amount is not defined");
    }

    @Test
    void shouldThrowErrorIfPreviousMonthRateChangeNotDefined() {
        MutualFundInvestment investment = new MutualFundInvestment(MutualFundType.EQUITY, 10000.0);
        investment.setSipAmount(1000.0);

        assertThrows(IllegalStateException.class, () -> investment.applyRateChange(Month.FEBRUARY, new Float(10.0)), "Cannot apply rate change as previous month's rate change not defined");
    }

    @Test
    void shouldApplyRateChangeForJanuary() {
        MutualFundInvestment investment = new MutualFundInvestment(MutualFundType.EQUITY, 10000.0);
        investment.setSipAmount(1000.0);

        investment.applyRateChange(Month.JANUARY, 10.0F);

        Double balance = investment.getBalanceFor(Month.JANUARY);

        assertEquals(11000, balance, 0.00001);
    }

    @Test
    void shouldApplyRateChangeForOtherMonths() {
        MutualFundInvestment investment = new MutualFundInvestment(MutualFundType.EQUITY, 10000.0);
        investment.setSipAmount(1000.0);

        investment.applyRateChange(Month.JANUARY, 10.0F);
        investment.applyRateChange(Month.FEBRUARY, 8.5F); // 13020
        investment.applyRateChange(Month.MARCH, -19.0F); // 11356.2

        Double balance = investment.getBalanceFor(Month.MARCH);

        assertEquals(11356.0, balance, 0.00001);
    }

    @Test
    void shouldSetBalanceForAMonth() {
        MutualFundInvestment investment = new MutualFundInvestment(MutualFundType.EQUITY, 10000.0);
        investment.setSipAmount(1000.0);
        investment.applyRateChange(Month.JANUARY, 10.0F);
        Double balance = investment.getBalanceFor(Month.JANUARY);

        assertEquals(11000.0, balance, 0.00001);

        investment.setBalanceFor("JANUARY", 1576.0);

        balance = investment.getBalanceFor(Month.JANUARY);

        assertEquals(1576.0, balance, 0.00001);
    }
}