package programs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_KEY = "https://exchangerate.host/"; 

    private static Map<String, Double> favoriteCurrencies = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Favorite Currency");
            System.out.println("2. View Favorite Currencies");
            System.out.println("3. Update Favorite Currency");
            System.out.println("4. Convert Currency");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addFavoriteCurrency();
                    break;
                case 2:
                    viewFavoriteCurrencies();
                    break;
                case 3:
                    updateFavoriteCurrency();
                    break;
                case 4:
                    convertCurrency();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addFavoriteCurrency() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter currency code: ");
        String currencyCode = scanner.nextLine().toUpperCase();
        System.out.print("Enter exchange rate: ");
        double exchangeRate = scanner.nextDouble();
        favoriteCurrencies.put(currencyCode, exchangeRate);
        System.out.println(currencyCode + " added to favorites.");
    }

    private static void viewFavoriteCurrencies() {
        System.out.println("\nFavorite Currencies:");
        for (Map.Entry<String, Double> entry : favoriteCurrencies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void updateFavoriteCurrency() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter currency code to update: ");
        String currencyCode = scanner.nextLine().toUpperCase();
        if (favoriteCurrencies.containsKey(currencyCode)) {
            System.out.print("Enter new exchange rate: ");
            double exchangeRate = scanner.nextDouble();
            favoriteCurrencies.put(currencyCode, exchangeRate);
            System.out.println(currencyCode + " updated.");
        } else {
            System.out.println("Currency not found in favorites.");
        }
    }

    private static void convertCurrency() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount in USD: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter target currency code: ");
        String targetCurrency = scanner.next().toUpperCase();
        
        if (favoriteCurrencies.containsKey(targetCurrency)) {
            double exchangeRate = favoriteCurrencies.get(targetCurrency);
            double convertedAmount = amount * exchangeRate;
            System.out.printf("%.2f USD is equivalent to %.2f %s\n", amount, convertedAmount, targetCurrency);
        } else {
            System.out.println("Currency not found in favorites. Unable to convert.");
        }
    }
}
