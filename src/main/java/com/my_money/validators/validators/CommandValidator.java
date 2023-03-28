package com.my_money.validators.validators;

import com.my_money.models.Command;
import com.my_money.models.MutualFunds;
import com.my_money.models.Portfolio;

import java.util.Map;

public class CommandValidator {
    private final MutualFunds mutualFunds;

    public CommandValidator(MutualFunds mutualFunds) {
        this.mutualFunds = mutualFunds;
    }

    public void validate(Command command, String[] inputArray, Portfolio portfolio) {
        Integer numberOfInputRequired = numberOfInputRequired(command);

        if(numberOfInputRequired != (inputArray.length - 1)) {
            throw new IllegalArgumentException(command + " command requires " + numberOfInputRequired + " inputs");
        }

        if(portfolioRequired(command) && portfolio == null) {
            throw new IllegalStateException("Portfolio is not defined");
        }

    }

    private Integer numberOfInputRequired(Command command) {
        return Map.of(
                Command.ALLOCATE, 3,
                Command.SIP, 3,
                Command.CHANGE, 4,
                Command.BALANCE, 1,
                Command.REBALANCE, 0
        ).get(command);
    }

    private boolean portfolioRequired(Command command) {
        return command != Command.ALLOCATE;
    }
}
