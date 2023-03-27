package org.example;

import java.util.ArrayList;
import java.util.Optional;

public class Portfolio {
    private ArrayList<MutualFundInvestment> investments;

    public Portfolio(Double equity, Double debt, Double gold) {
        MutualFundInvestment equityInvestment = new MutualFundInvestment(MutualFundType.EQUITY, equity);
        MutualFundInvestment debtInvestment = new MutualFundInvestment(MutualFundType.DEBT, debt);
        MutualFundInvestment goldInvestment = new MutualFundInvestment(MutualFundType.GOLD, gold);

        this.investments.add(equityInvestment);
        this.investments.add(debtInvestment);
        this.investments.add(goldInvestment);
    }

    public void sip(Double equity, Double debt, Double gold) {
        Optional<MutualFundInvestment> equityInvestment = this.getInvestmentOfType(MutualFundType.EQUITY);
        equityInvestment.ifPresent(investment -> investment.setSipAmount(equity));

        Optional<MutualFundInvestment> debtInvestment = this.getInvestmentOfType(MutualFundType.DEBT);
        debtInvestment.ifPresent(investment -> investment.setSipAmount(debt));

        Optional<MutualFundInvestment> goldInvestment = this.getInvestmentOfType(MutualFundType.GOLD);
        goldInvestment.ifPresent(investment -> investment.setSipAmount(gold));
    }

    public Optional<MutualFundInvestment> getInvestmentOfType(MutualFundType type){
        return this.investments.stream().filter(mutualFundInvestment -> mutualFundInvestment.getType().equals(type)).findFirst();
    }
}
