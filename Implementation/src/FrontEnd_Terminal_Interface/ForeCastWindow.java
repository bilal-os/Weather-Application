package FrontEnd_Terminal_Interface;

import FrontEnd_Terminal_Interface.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class ForeCastWindow {

    private Vector<WeatherData> forecasts;

    public ForeCastWindow() {
        forecasts = new Vector<>();
    }

    public void showForecasts(String forecastReports) {
        try {
            JSONObject jsonForecastList = new JSONObject(forecastReports);
            JSONArray jsonForecasts = jsonForecastList.getJSONArray("list");
            for (int i = 0; i < jsonForecasts.length(); i++) {
                String weatherReport = jsonForecasts.getJSONObject(i).toString();
                WeatherData weatherData = new WeatherData();
                weatherData.formatData(weatherReport);
                forecasts.add(weatherData);
            }

            showDailyForecasts();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private void showDailyForecasts() {
        System.out.println("\n                    Forecasts for the Next Five Days:\n");

        Map<String, List<WeatherData>> dailyForecasts = groupForecastsByDay();

        for (Map.Entry<String, List<WeatherData>> entry : dailyForecasts.entrySet()) {
            String date = entry.getKey();
            List<WeatherData> dailyData = entry.getValue();

            System.out.println("\n                  Forecast for " + date + ":");
            System.out.println("Average Temperature: " + calculateAverageTemperature(dailyData) + "°F");
            System.out.println("Minimum Temperature: " + calculateMinTemperature(dailyData) + "°F");
            System.out.println("Maximum Temperature: " + calculateMaxTemperature(dailyData) + "°F");
            System.out.println("Average Humidity: " + calculateAverageHumidity(dailyData) + "%");
            System.out.println("Average Wind Speed: " + calculateAverageWindSpeed(dailyData) + " m/s");
            System.out.println("Average Visibility: " + calculateAverageVisibility(dailyData) + " meters");
            System.out.println("Total Rainfall: " + calculateTotalRainfall(dailyData) + " mm");
            System.out.println("Total Snowfall: " + calculateTotalSnowfall(dailyData) + " mm");
            System.out.println("Average Cloudiness: " + calculateAverageCloudiness(dailyData) + "%\n");
        }
        System.out.println("\n>>Press '1' to get back to the main menu.");

        // Handle user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("1")) {
                break;
            }
        }
    }

    private Map<String, List<WeatherData>> groupForecastsByDay() {
        Map<String, List<WeatherData>> dailyForecasts = new HashMap<>();
        for (WeatherData forecast : forecasts) {
            String dateKey = forecast.getReadableDate();

            if (!dailyForecasts.containsKey(dateKey)) {
                dailyForecasts.put(dateKey, new ArrayList<>());
            }
            dailyForecasts.get(dateKey).add(forecast);
        }
        return dailyForecasts;
    }

    private double calculateAverageTemperature(List<WeatherData> data) {
        double totalTemp = 0.0;
        for (WeatherData forecast : data) {
            totalTemp += forecast.temp;
        }
        return totalTemp / data.size();
    }

    private double calculateMinTemperature(List<WeatherData> data) {
        double minTemp = Double.MAX_VALUE;
        for (WeatherData forecast : data) {
            minTemp = Math.min(minTemp, forecast.temp_min);
        }
        return minTemp;
    }

    private double calculateMaxTemperature(List<WeatherData> data) {
        double maxTemp = Double.MIN_VALUE;
        for (WeatherData forecast : data) {
            maxTemp = Math.max(maxTemp, forecast.temp_max);
        }
        return maxTemp;
    }

    private int calculateAverageHumidity(List<WeatherData> data) {
        int totalHumidity = 0;
        for (WeatherData forecast : data) {
            totalHumidity += forecast.humidity;
        }
        return totalHumidity / data.size();
    }

    private double calculateAverageWindSpeed(List<WeatherData> data) {
        double totalWindSpeed = 0.0;
        for (WeatherData forecast : data) {
            totalWindSpeed += forecast.speed;
        }
        return totalWindSpeed / data.size();
    }

    private int calculateAverageVisibility(List<WeatherData> data) {
        int totalVisibility = 0;
        for (WeatherData forecast : data) {
            totalVisibility += forecast.visibility;
        }
        return totalVisibility / data.size();
    }

    private double calculateTotalRainfall(List<WeatherData> data) {
        double totalRainfall = 0.0;
        for (WeatherData forecast : data) {
            totalRainfall += forecast.rainh1;
        }
        return totalRainfall;
    }

    private double calculateTotalSnowfall(List<WeatherData> data) {
        double totalSnowfall = 0.0;
        for (WeatherData forecast : data) {
            totalSnowfall += forecast.snowh1;
        }
        return totalSnowfall;
    }

    private int calculateAverageCloudiness(List<WeatherData> data) {
        int totalCloudiness = 0;
        for (WeatherData forecast : data) {
            totalCloudiness += forecast.cloudsall;
        }
        return totalCloudiness / data.size();
    }
}
