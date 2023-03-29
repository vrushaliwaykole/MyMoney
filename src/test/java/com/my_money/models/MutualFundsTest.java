package com.my_money.models;

import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class MutualFundsTest {

    @Test
    void shouldAddAndRateChangeForAllMutualFunds() {
        MutualFunds mutualFunds = new MutualFunds();
        mutualFunds.addRateChange("JANUARY", "10.0", "12.0", "34.0");

        assertEquals(10.0, mutualFunds.getRateChangeFor(Month.JANUARY, MutualFundType.EQUITY).get(), 0.00001);
        assertEquals(12.0, mutualFunds.getRateChangeFor(Month.JANUARY, MutualFundType.DEBT).get(), 0.00001);
        assertEquals(34.0, mutualFunds.getRateChangeFor(Month.JANUARY, MutualFundType.GOLD).get(), 0.00001);
    }
}