package com.my_money.models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {

    @Nested
    @DisplayName("Portfolio rebalance")
    class Rebalance {

        MutualFunds mutualFunds = new MutualFunds();
        Portfolio portfolio = new Portfolio(6000.0, 3000.0, 1000.0);

        @BeforeEach
        void setUp() {
            mutualFunds.addRateChange("JANUARY", "4.0", "10.0", "2.0");
            mutualFunds.addRateChange("FEBRUARY", "-10.0", "40.0", "0.0");
            mutualFunds.addRateChange("MARCH", "12.50", "12.50", "12.50");
            mutualFunds.addRateChange("APRIL", "8.0", "-3.0", "7.0");
            mutualFunds.addRateChange("MAY", "13.0", "21.0", "10.5");
            mutualFunds.addRateChange("JUNE", "10.00", "8.0", "-5.0");

            portfolio.sip(2000.0, 1000.0, 500.0);
            portfolio.applyRateChange("JANUARY", mutualFunds);
            portfolio.applyRateChange("FEBRUARY", mutualFunds);
            portfolio.applyRateChange("MARCH", mutualFunds);
            portfolio.applyRateChange("APRIL", mutualFunds);
            portfolio.applyRateChange("MAY", mutualFunds);
            portfolio.applyRateChange("JUNE", mutualFunds);
        }

        @Test
        void shouldRebalanceTheAmountForJune() {
            assertArrayEquals(new Double[]{6240.0, 3300.0, 1020.0}, portfolio.getBalanceFor("JANUARY"));
            assertArrayEquals(new Double[]{7416.0, 6020.0, 1520.0}, portfolio.getBalanceFor("FEBRUARY"));
            assertArrayEquals(new Double[]{10593.0, 7897.0, 2272.0}, portfolio.getBalanceFor("MARCH"));
            assertArrayEquals(new Double[]{13600.0, 8630.0, 2966.0}, portfolio.getBalanceFor("APRIL"));
            assertArrayEquals(new Double[]{17628.0, 11652.0, 3829.0}, portfolio.getBalanceFor("MAY"));
            assertArrayEquals(new Double[]{23619.0, 11809.0, 3936.0}, portfolio.getBalanceFor("JUNE"));

        }
        @Test
        void shouldGetLastRebalancedAmount() {
            assertArrayEquals(new Double[]{23619.0, 11809.0, 3936.0}, portfolio.getLastRebalanceAmounts());
        }

        @Test
        void shouldGetLastRebalancedAmountWhenValuesArePresentTilDecember() {
            mutualFunds.addRateChange("JULY", "-4.0", "9.0", "2.0");
            mutualFunds.addRateChange("AUGUST", "10.0", "6.4", "0.5");
            mutualFunds.addRateChange("SEPTEMBER", "9.0", "9.0", "9.0");
            mutualFunds.addRateChange("OCTOBER", "8.0", "-3.0", "7.0");
            mutualFunds.addRateChange("NOVEMBER", "8.4", "6.0", "2.0");
            mutualFunds.addRateChange("DECEMBER", "13.00", "3.0", "-10.0");

            portfolio.applyRateChange("JULY", mutualFunds);
            portfolio.applyRateChange("AUGUST", mutualFunds);
            portfolio.applyRateChange("SEPTEMBER", mutualFunds);
            portfolio.applyRateChange("OCTOBER", mutualFunds);
            portfolio.applyRateChange("NOVEMBER", mutualFunds);
            portfolio.applyRateChange("DECEMBER", mutualFunds);

            assertArrayEquals(new Double[]{49480.0, 24740.0, 8246.0}, portfolio.getLastRebalanceAmounts());
        }
    }
}