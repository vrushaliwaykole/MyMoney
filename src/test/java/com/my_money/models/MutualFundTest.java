package com.my_money.models;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MutualFundTest {
    @Test
    void shouldAddAndReturnRateChangeForGivenMonth() {
        MutualFund mutualFund = new MutualFund(MutualFundType.EQUITY);

        mutualFund.addRateChange("FEBRUARY", 89);
        mutualFund.addRateChange("DECEMBER", 10);

        Float rateChangeDecember = mutualFund.getRateChange(Month.DECEMBER).get();
        Float rateChangeFeb = mutualFund.getRateChange(Month.FEBRUARY).get();

        assertEquals(10.0, rateChangeDecember, 0.00001);
        assertEquals(89.0, rateChangeFeb, 0.000001);
    }

}