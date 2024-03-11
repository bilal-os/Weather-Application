package BusinessLogic.src;

public class DataManager {

    public static class DataManagerInterface {
        private Data_Manager dataManager;

        public DataManagerInterface()
        {
            dataManager = new Data_Manager();
        }
        public String fetchWeatherReport(double latitude, double longitude)
        {
            return dataManager.fetchWeatherReport(latitude,longitude);
        }

    }

    private static class Data_Manager {
        private WeatherService.Weather_Service weatherService;

        public Data_Manager()
        {
            weatherService = new WeatherService.Weather_Service();
        }
        public String fetchWeatherReport(double latitude, double longitude) {
            return weatherService.fetchWeatherReport(latitude,longitude);
        }

    }

}
