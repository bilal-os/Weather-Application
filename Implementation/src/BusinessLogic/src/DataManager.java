package BusinessLogic.src;
import CacheStorage_DataBase.CacheStorage;

import java.util.Vector;

public class DataManager {

    abstract public static class DataManagerInterface {

        abstract public String fetchWeatherReport(double latitude, double longitude);
        abstract  public String fetchAirReport(double latitude, double longitude);
        abstract  public String fetchForecast(double latitude, double longitude);


    }

    public static class Data_Manager extends DataManagerInterface{
        private WeatherService weatherService;
        private CacheManager cacheManager;

        public Data_Manager(CacheManager cacheManager)
        {
            weatherService = new WeatherService();
            this.cacheManager=cacheManager;
        }
        public String fetchWeatherReport(double latitude, double longitude) {

            String cacheData = cacheManager.fetchWeatherReport(latitude,longitude);

            if("No weather report found".equals(cacheData)) {
                return weatherService.fetchWeatherReport(latitude, longitude);
            }

            return cacheData;
        }

        public String fetchAirReport(double latitude, double longitude){

            String cacheData = cacheManager.fetchAirReport(latitude,longitude);

            if("No air report found".equals(cacheData)) {
                return weatherService.fetchAirReport(latitude, longitude);
            }
            return cacheData;
        }

        public String fetchForecast(double latitude, double longitude)
        {
            String cacheData = cacheManager.fetchForecast(latitude,longitude);

            if("No forecast report found".equals(cacheData)) {
                return weatherService.fetchForecast(latitude, longitude);
            }

            return cacheData;
        }

        public Vector<String> fetchWeatherReports(Vector<String> coordinates)
        {
            Vector<String> reports = new Vector<>();

            for (String coordinate : coordinates) {
                String[] parts = coordinate.split(",");
                if (parts.length == 2) {
                    double latitude = Double.parseDouble(parts[0].trim());
                    double longitude = Double.parseDouble(parts[1].trim());
                    String airReport = weatherService.fetchWeatherReport(latitude, longitude);
                    if (airReport != null) {
                        reports.add(airReport);
                    }
                }
            }

            return reports;
        }

        public Vector<String> fetchAirReports(Vector<String> coordinates) {
            Vector<String> reports = new Vector<>();

            for (String coordinate : coordinates) {
                String[] parts = coordinate.split(",");
                if (parts.length == 2) {
                    double latitude = Double.parseDouble(parts[0].trim());
                    double longitude = Double.parseDouble(parts[1].trim());
                    String airReport = weatherService.fetchAirReport(latitude, longitude);
                    if (airReport != null) {
                        reports.add(airReport);
                    }
                }
            }

            return reports;
        }

        public Vector<String> fetchForecastReports(Vector<String> coordinates)
        {
            Vector<String> reports = new Vector<>();

            for (String coordinate : coordinates) {
                String[] parts = coordinate.split(",");
                if (parts.length == 2) {
                    double latitude = Double.parseDouble(parts[0].trim());
                    double longitude = Double.parseDouble(parts[1].trim());
                    String airReport = weatherService.fetchForecast(latitude, longitude);
                    if (airReport != null) {
                        reports.add(airReport);
                    }
                }
            }

            return reports;
        }

        public boolean storeWeatherReport(double latitude, double longitude,String weatherReport)
        {
          return cacheManager.storeWeatherReport(latitude,longitude,weatherReport);
        }

        public boolean storeAirReport(double latitude, double longitude,String airReport)
        {
            return cacheManager.storeAirReport(latitude,longitude,airReport);
        }

        public boolean storeForecast(double latitude, double longitude, String forecast)
        {
           return cacheManager.storeForecast(latitude,longitude,forecast);
        }


    }

}
