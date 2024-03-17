package BusinessLogic.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class GeoCoding {

    private String baseURL_reverse = "http://api.openweathermap.org/geo/1.0/reverse?";
    private String baseURL_direct = "http://api.openweathermap.org/geo/1.0/direct?";
    public String reverseGeoCoding(double latitude, double longitude){

        String msg = "";

        try {
            // Construct URI with latitude, longitude, and API key
            URI uri = new URI(baseURL_reverse + "lat=" + latitude + "&lon=" + longitude + "&limit=1&appid=ace110a273e85c2a8eb1414bf74153f9");

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
            return "Error";
        }

    }
    public String directGeoCoding(String location)
    {
        String msg = "";

        try {
            // Construct URI with latitude, longitude, and API key
            URI uri = new URI(baseURL_direct + "q=" + location + "&appid=ace110a273e85c2a8eb1414bf74153f9");

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
