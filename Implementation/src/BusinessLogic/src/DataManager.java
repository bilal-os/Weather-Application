package BusinessLogic.src;

import java.util.Vector;

public class DataManager {

    abstract public static class DataManagerInterface {

        abstract public String fetchWeatherReport(double latitude, double longitude);
        abstract  public String fetchAirReport(double latitude, double longitude);
        abstract  public String fetchForecast(double latitude, double longitude);

    }

    public static class Data_Manager extends DataManagerInterface{
        private APIService weatherService;
        private CacheManager cacheManager;

        public Data_Manager(CacheManager cacheManager)
        {
            weatherService = new APIService();
            this.cacheManager=cacheManager;
        }
        public String fetchWeatherReport(double latitude, double longitude) {
            try {
                String cacheData = cacheManager.fetchWeatherReport(latitude, longitude);

                if ("No weather report found".equals(cacheData)) {
                    return weatherService.fetchWeatherReport(latitude, longitude);
                }

                return cacheData;
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }

        public String fetchAirReport(double latitude, double longitude){

            try {
                String cacheData = cacheManager.fetchAirReport(latitude, longitude);

                if ("No air report found".equals(cacheData)) {
                    return weatherService.fetchAirReport(latitude, longitude);
                }
                return cacheData;
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }

        public String fetchForecast(double latitude, double longitude)
        {
            try {
                String cacheData = cacheManager.fetchForecast(latitude, longitude);

                if ("No forecast report found".equals(cacheData)) {
                    return weatherService.fetchForecast(latitude, longitude);
                }

                return cacheData;
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }

        public Vector<String> fetchWeatherReports(Vector<String> coordinates)
        {
            try {
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
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }

        public Vector<String> fetchAirReports(Vector<String> coordinates) {

            try {


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
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }

        public Vector<String> fetchForecastReports(Vector<String> coordinates)
        {
            try {

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
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }

        private boolean storeWeatherReport(double latitude, double longitude,String weatherReport)
        {
            try {
                return cacheManager.storeWeatherReport(latitude, longitude, weatherReport);
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return false;
            }
        }

        private boolean storeAirReport(double latitude, double longitude,String airReport)
        {
            try {
                return cacheManager.storeAirReport(latitude,longitude,airReport);
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return false;
            }

        }

        private boolean storeForecast(double latitude, double longitude, String forecast)
        {
            try {
                return cacheManager.storeForecast(latitude, longitude, forecast);
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return false;
            }
        }


    }

}
