package BusinessLogic.src;

import java.util.Vector;

public class DataManager {

    abstract public static class DataManagerInterface {

        abstract public String fetchWeatherReport(double latitude, double longitude);
        abstract  public String fetchAirReport(double latitude, double longitude);
        abstract  public String fetchForecast(double latitude, double longitude);

        abstract public Vector<String> fetchWeatherReports(Vector<String> coordinates);
        abstract public Vector<String> fetchAirReports(Vector<String> coordinates);

        abstract public Vector<String> fetchForecastReports(Vector<String> coordinates);

    }

    public static class Data_Manager extends DataManagerInterface{
        private WeatherService weatherService;

        public Data_Manager()
        {
            weatherService = new WeatherService();
        }
        public String fetchWeatherReport(double latitude, double longitude) {
            return weatherService.fetchWeatherReport(latitude,longitude);
        }

        public String fetchAirReport(double latitude, double longitude){
            return weatherService.fetchAirReport(latitude,longitude);
        }

        public String fetchForecast(double latitude, double longitude)
        {
            return weatherService.fetchForecast(latitude,longitude);
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

    }

}
