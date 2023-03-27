package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Month;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/vrushaliwaykole/MyMoney/input.txt");
        Scanner inputScanner = new Scanner(inputStream);
        Portfolio portfolio = null;

        MutualFund equityMutualFund = new MutualFund(MutualFundType.EQUITY);
        MutualFund debtMutualFund = new MutualFund(MutualFundType.DEBT);
        MutualFund goldMutualFund = new MutualFund(MutualFundType.GOLD);

        while (inputScanner.hasNextLine()) {
            String input = inputScanner.nextLine();
            String[] inputArray = input.split(" ");
            String command = inputArray[0];
            switch (command) {
                case "ALLOCATE":
                    if (inputArray.length != 4) {
                        throw new IllegalArgumentException("ALLOCATE command needs amount for equity, debt and gold funds");
                    }
                    portfolio = new Portfolio(Double.parseDouble(inputArray[1]), Double.parseDouble(inputArray[2]), Double.parseDouble(inputArray[3]));
                    break;
                case "SIP":
                    if (inputArray.length != 4) {
                        throw new IllegalArgumentException("SIP command needs sip amount for equity, debt and gold funds");
                    }
                    if (portfolio == null) {
                        throw new IllegalStateException("Portfolio is not defined");
                    }
                    portfolio.sip(Double.parseDouble(inputArray[1]), Double.parseDouble(inputArray[2]), Double.parseDouble(inputArray[3]));
                    break;
                case "CHANGE":
                    if (inputArray.length != 5) {
                        throw new IllegalArgumentException("CHANGE command needs percentage for equity, debt and gold funds and month for which change has occurred");
                    }
                    if (portfolio == null) {
                        throw new IllegalStateException("Portfolio is not defined");
                    }
                    String month = inputArray[4];
                    equityMutualFund.addRateChange(month, Float.parseFloat(inputArray[1]));
                    debtMutualFund.addRateChange(month, Float.parseFloat(inputArray[2]));
                    goldMutualFund.addRateChange(month, Float.parseFloat(inputArray[3]));
                    break;
                case "BALANCE":
                    if (inputArray.length != 2) {
                        throw new IllegalArgumentException("BALANCE command needs month for which balance needs to be calculated");
                    }
                    if (portfolio == null) {
                        throw new IllegalStateException("Portfolio is not defined");
                    }
                    
                    break;
                case "REBALANCE":
                    if (portfolio == null) {
                        throw new IllegalStateException("Portfolio is not defined");
                    }
                    break;
                default:
                    throw new UnsupportedOperationException("Specified command is not supported by the application.");
            }

        }
        inputScanner.close();
    }
}