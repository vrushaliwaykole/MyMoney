package org.example;

import java.time.Month;
import java.util.ArrayList;

enum MutualFundType {
    EQUITY,
    DEBT,
    GOLD
}
public class MutualFund {
    private MutualFundType type; //
    private ArrayList<RateChange> rateChanges;

    public MutualFund(MutualFundType type) {
        this.type = type;
    }

    public void addRateChange(String month, float percentage) {
        this.rateChanges.add(new RateChange(Month.valueOf(month), percentage));
    }
}
