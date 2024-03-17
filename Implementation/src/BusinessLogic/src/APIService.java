package BusinessLogic.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
public class APIService {
    private String apiKey = "ace110a273e85c2a8eb1414bf74153f9";
    private String baseWeatherURL = "https://api.openweathermap.org/data/2.5/weather?";
    private String baseAirURL = "http://api.openweathermap.org/data/2.5/air_pollution?";
    private String baseForecastURL = "https://api.openweathermap.org/data/2.5/forecast?";

    private String buildURL(String baseURL, double latitude, double longitude) {
        return baseURL + "lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;
    }

    private String fetchData(String urlString) throws Exception{
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

    public String fetchWeatherReport(double latitude, double longitude) throws Exception{
        return fetchData(buildURL(baseWeatherURL, latitude, longitude));
    }

    public String fetchAirReport(double latitude, double longitude) throws Exception{
        return fetchData(buildURL(baseAirURL, latitude, longitude));
    }

    public String fetchForecast(double latitude, double longitude) throws Exception{
        return fetchData(buildURL(baseForecastURL, latitude, longitude));
    }

}
