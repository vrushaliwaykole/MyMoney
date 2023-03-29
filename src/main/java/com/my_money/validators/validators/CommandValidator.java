package com.my_money.validators.validators;

import com.my_money.models.Command;
import com.my_money.models.MutualFunds;
import com.my_money.models.Portfolio;

import java.util.HashMap;
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
        HashMap<Command, Integer> result = new HashMap<>();
        result.put(Command.ALLOCATE, 3);
        result.put(Command.SIP, 3);
        result.put(Command.CHANGE, 4);
        result.put(Command.BALANCE, 1);
        result.put(Command.REBALANCE, 0);
        return result.get(command);
    }

    private boolean portfolioRequired(Command command) {
        return command != Command.ALLOCATE;
    }
}
