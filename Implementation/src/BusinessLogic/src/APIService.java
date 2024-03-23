package BusinessLogic.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIService {
    private String apiKey = "ace110a273e85c2a8eb1414bf74153f9";
    private String baseWeatherURL = "https://api.openweathermap.org/data/2.5/weather?";
    private String baseAirURL = "http://api.openweathermap.org/data/2.5/air_pollution?";
    private String baseForecastURL = "https://api.openweathermap.org/data/2.5/forecast?";
    private String baseReverseGeoURL = "http://api.openweathermap.org/geo/1.0/reverse?";
    private String baseDirectGeoURL = "http://api.openweathermap.org/geo/1.0/direct?";

    private String buildURL(String baseURL, double latitude, double longitude) {

        return baseURL + "lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;
    }

    private String fetchData(String urlString) throws Exception {
        try {
            // Create URL object from urlString
            URL url = new URL(urlString);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // Read response into StringBuilder
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            // Close connection
            connection.disconnect();

            return responseBuilder.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    public String fetchReport(double latitude, double longitude, String reportType) throws Exception {
        String urlString = buildURL(getBaseURL(reportType), latitude, longitude);
        return fetchData(urlString);
    }

    public String reverseGeoCoding(double latitude, double longitude) throws Exception {
        String urlString = baseReverseGeoURL + "lat=" + latitude + "&lon=" + longitude + "&limit=1&appid=" + apiKey;
        return fetchData(urlString);
    }

    public String directGeoCoding(String location) throws Exception {
        String urlString = baseDirectGeoURL + "q=" + location + "&appid=" + apiKey;
        return fetchData(urlString);
    }

    private String getBaseURL(String reportType) throws Exception {
        switch (reportType) {
            case "Weather":
                return baseWeatherURL;
            case "Air":
                return baseAirURL;
            case "Forecast":
                return baseForecastURL;
            default:
                throw new Exception("Invalid report type: " + reportType);
        }
    }
}
