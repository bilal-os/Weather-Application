package BusinessLogic.src;

import java.util.Vector;

abstract public class CacheManager {
   abstract public boolean storeWeatherReport(double latitude, double longitude, String WeatherReport) throws Exception;
   abstract public boolean storeAirReport(double latitude, double longitude, String airReport) throws Exception;

   abstract public boolean storeForecast(double latitude, double longitude, String forecast) throws Exception;

   abstract public String fetchWeatherReport(double latitude, double longitude) throws Exception;

   abstract public String fetchAirReport(double latitude, double longitude) throws Exception;

   abstract public String fetchForecast(double latitude, double longitude) throws Exception;

   abstract public boolean storeLocation(String locationDetails) throws Exception;

   abstract public Vector<String> fetchStoredLocations() throws Exception;


}
