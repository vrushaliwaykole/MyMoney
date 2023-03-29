package com.my_money;

import com.my_money.models.Command;
import com.my_money.models.MutualFunds;
import com.my_money.models.Portfolio;
import com.my_money.validators.validators.CommandValidator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        Scanner inputScanner = new Scanner(inputStream);
        Portfolio portfolio = null;

        MutualFunds mutualFunds = new MutualFunds();

        CommandValidator validator = new CommandValidator(mutualFunds);

        while (inputScanner.hasNextLine()) {
            String input = inputScanner.nextLine();
            String[] inputArray = input.split(" ");
            Command command = Command.valueOf(inputArray[0]);

            validator.validate(command, inputArray, portfolio);

            switch (command) {
                case ALLOCATE:
                    portfolio = new Portfolio(Double.parseDouble(inputArray[1]), Double.parseDouble(inputArray[2]), Double.parseDouble(inputArray[3]));
                    break;
                case SIP:
                    portfolio.sip(Double.parseDouble(inputArray[1]), Double.parseDouble(inputArray[2]), Double.parseDouble(inputArray[3]));
                    break;
                case CHANGE:
                    String month = inputArray[4];
                    mutualFunds.addRateChange(month, inputArray[1].replace("%", ""), inputArray[2].replace("%", ""), inputArray[3].replace("%", ""));
                    portfolio.applyRateChange(month, mutualFunds);
                    break;
                case BALANCE:
                    Double[] result = portfolio.getBalanceFor(inputArray[1]);
                    System.out.println(result[0] + " " + result[1] + " " + result[2]);
                    break;
                case REBALANCE:
                    result = portfolio.getLastRebalanceAmounts();
                    System.out.println(result[0] + " " + result[1] + " " + result[2]);
                    break;
            }

        }
        inputScanner.close();
    }
}