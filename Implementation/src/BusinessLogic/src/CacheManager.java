package BusinessLogic.src;

abstract public class CacheManager {
   abstract public boolean storeWeatherReport(double latitude, double longitude, String WeatherReport);
   abstract public boolean storeAirReport(double latitude, double longitude, String airReport);

   abstract public boolean storeForecast(double latitude, double longitude, String forecast);

}
