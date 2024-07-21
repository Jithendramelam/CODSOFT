import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CurrencyConverter {
    private static final String API_URL = "https://api.gemini.com/v1/";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., btc): ");
        String baseCurrency = scanner.nextLine().toLowerCase();

        System.out.print("Enter target currency (e.g., usd): ");
        String targetCurrency = scanner.nextLine().toLowerCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            if (isCurrencyPairSupported(baseCurrency, targetCurrency)) {
                double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
                double convertedAmount = convertCurrency(amount, exchangeRate);

                System.out.printf("Converted Amount: %.2f %s\n", convertedAmount, targetCurrency.toUpperCase());
            } else {
                System.out.println("Error: Currency pair not supported.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    private static boolean isCurrencyPairSupported(String baseCurrency, String targetCurrency) throws Exception {
        String urlString = API_URL + "symbols";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        JSONArray json = new JSONArray(content.toString());
        String pair = baseCurrency + targetCurrency;
        for (int i = 0; i < json.length(); i++) {
            if (json.getString(i).equals(pair)) {
                return true;
            }
        }
        return false;
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String urlString = API_URL + "pubticker/" + baseCurrency + targetCurrency;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        JSONObject json = new JSONObject(content.toString());
        return Double.parseDouble(json.getString("last"));
    }

    private static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}
