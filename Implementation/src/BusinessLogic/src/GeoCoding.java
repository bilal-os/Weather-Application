package BusinessLogic.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class GeoCoding {

    private String baseReverseURL = "http://api.openweathermap.org/geo/1.0/reverse?";
    private String baseDirectURL = "http://api.openweathermap.org/geo/1.0/direct?";
    private String apiKey = "ace110a273e85c2a8eb1414bf74153f9";


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

    public String reverseGeoCoding(double latitude, double longitude) throws Exception{

            return fetchData(baseReverseURL + "lat=" + latitude + "&lon=" + longitude + "&limit=1&appid=" + apiKey);

    }
    public String directGeoCoding(String location) throws Exception
    {

            return fetchData(baseDirectURL + "q=" + location + "&appid=" + apiKey);

    }

}
