package com.nt;

import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        try {
            System.out.println("Enter base currency (e.g., USD): ");
            String baseCurrency = scanner.nextLine().toUpperCase();

            System.out.println("Enter target currency (e.g., EUR): ");
            String targetCurrency = scanner.nextLine().toUpperCase();

            System.out.println("Enter amount to convert: ");
            double amount = scanner.nextDouble();

            double rate = exchangeRateService.getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * rate;

            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.err.println("Error fetching exchange rate: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
