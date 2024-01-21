package com.CodSoft.InternshipJavaProgramming4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter_4 {
    @SuppressWarnings("unused")
	private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        try {
            String baseCurrency = getUserInput("Enter the base currency code: ");
            String targetCurrency = getUserInput("Enter the target currency code: ");

            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

            double amountToConvert = Double.parseDouble(getUserInput("Enter the amount to convert: "));

            double convertedAmount = amountToConvert * exchangeRate;

            System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String getUserInput(String message) throws IOException {
        System.out.print(message);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String apiUrl = API_URL + baseCurrency;
        @SuppressWarnings("deprecation")
		URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

                        String jsonResponse = response.toString();
            double exchangeRate = parseExchangeRate(jsonResponse, targetCurrency);

            return exchangeRate;
        } else {
            throw new IOException("Failed to fetch exchange rates. HTTP Error Code: " + responseCode);
        }
    }

    private static double parseExchangeRate(String jsonResponse, String targetCurrency) {
        String rateKey = "rates";
        int rateIndex = jsonResponse.indexOf(rateKey);
        int startIndex = jsonResponse.indexOf(targetCurrency, rateIndex) + 5; // +5 to skip ": "
        int endIndex = jsonResponse.indexOf(",", startIndex);
        String exchangeRateString = jsonResponse.substring(startIndex, endIndex);
        return Double.parseDouble(exchangeRateString);
    }
}

