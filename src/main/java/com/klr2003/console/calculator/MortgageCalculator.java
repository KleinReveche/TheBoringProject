package com.klr2003.console.calculator;

import com.klr2003.console.handlers.ConsoleIOHandler;

import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {

    public MortgageCalculator() {
        getPrincipal();
        getMonthlyInterest();
        getNumberOfPayments();
        calculateMortgage();
    }

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    static int principal = 0;
    static float monthlyInterest = 0;
    static int numberOfPayments = 0;

    static Scanner scanner = new Scanner(System.in);

    private void getPrincipal() {
        while(true) {
            ConsoleIOHandler.systemPrinter("Principal: ");
            principal = scanner.nextInt();
            if (principal >= 1000 && principal <= 10_000_000)
                break;
            ConsoleIOHandler.printToConsole("Please input numbers between 1,000 and 10,000,000 only.");
        }
    }
    private void getMonthlyInterest() {
        while(true) {
            ConsoleIOHandler.systemPrinter("Annual Interest Rate: ");
            float annualInterest = scanner.nextFloat();
            if (annualInterest >= 1 && annualInterest <= 10) {
                monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
                break;
            }
            ConsoleIOHandler.printToConsole("Please input numbers between 0 and 10.0 only.");
        }
    }

    private void getNumberOfPayments() {
        while(true) {
            ConsoleIOHandler.systemPrinter("Period (Years): ");
            byte years = scanner.nextByte();
            if (years >= 0 && years <= 30) {
                numberOfPayments = years * MONTHS_IN_YEAR;
                break;
            }
            ConsoleIOHandler.printToConsole("Please input numbers between 0 and 30 only.");
        }
    }

    private void calculateMortgage() {
        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        ConsoleIOHandler.printToConsole("Your Mortgage is " + mortgageFormatted);
    }
}
