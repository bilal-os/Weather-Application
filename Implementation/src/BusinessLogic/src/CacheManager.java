package BusinessLogic.src;

import java.util.Vector;

abstract public class CacheManager {
   abstract public boolean storeWeatherReport(double latitude, double longitude, String WeatherReport);
   abstract public boolean storeAirReport(double latitude, double longitude, String airReport);

   abstract public boolean storeForecast(double latitude, double longitude, String forecast);

   abstract public String fetchWeatherReport(double latitude, double longitude);

   abstract public String fetchAirReport(double latitude, double longitude);

   abstract public String fetchForecast(double latitude, double longitude);

   abstract public boolean storeLocation(String locationDetails);

   abstract public Vector<String> fetchStoredLocations();


}
