package com.nt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ExchangeRateService {
	private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

	public double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
		String urlString = API_URL + baseCurrency;
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuilder content = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		in.close();
		connection.disconnect();

		// Print the response content to debug
		System.out.println("API Response: " + content.toString());

		JSONObject json = new JSONObject(content.toString());
		JSONObject rates = json.getJSONObject("rates");

		return rates.getDouble(targetCurrency);
	}
}
