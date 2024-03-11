package BusinessLogic.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
public class WeatherService {
    public static class Weather_Service{
        private String apiURL = "https://api.openweathermap.org/data/2.5/weather?";
        public String fetchWeatherReport(double latitude,double longitude)
        {
            try {
                // Construct URI with latitude, longitude, and API key
                URI uri = new URI(apiURL + "lat=" + latitude + "&lon=" + longitude + "&appid=ace110a273e85c2a8eb1414bf74153f9");

                // Create URL object from URI
                URL url = uri.toURL();

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
                e.printStackTrace();
                return null;
            }
        }
    }
}
