import BusinessLogic.src.DataManager;

import java.net.URLEncoder;
import java.util.Vector;

import BusinessLogic.src.GeoCoding;
import BusinessLogic.src.LocationManager;
import CacheStorage_DataBase.CacheStorage;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Create an instance of CacheStorage
            CacheStorage cacheStorage = new CacheStorage();

            // Test storeWeatherReport method
            double latitude = 40.7128; // Example latitude (New York City)
            double longitude = -74.0060; // Example longitude (New York City)
            String weatherReport = "Sunny"; // Example weather report
            boolean weatherStored = cacheStorage.storeWeatherReport(latitude, longitude, weatherReport);
            if (weatherStored) {
                System.out.println("Weather report stored successfully.");
            } else {
                System.out.println("Failed to store weather report.");
            }

            // Test fetchWeatherReport method
            String fetchedWeatherReport = cacheStorage.fetchWeatherReport(latitude, longitude);
            if (fetchedWeatherReport != null) {
                System.out.println("Fetched weather report: " + fetchedWeatherReport);
            } else {
                System.out.println("Failed to fetch weather report.");
            }

            // Add more tests as needed for other methods...

        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
