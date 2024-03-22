package FrontEnd_Terminal_Interface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

public class ForecastWindow {
    private Vector<WeatherData> forecasts;

    public ForecastWindow()
    {
        forecasts = new Vector<>();
    }

    private void formatData(String forecastData) throws Exception {

        try {
            JSONObject forecastJson = new JSONObject(forecastData);
            JSONArray forecastList = forecastJson.getJSONArray("list");

            for (int i = 0; i < forecastList.length(); i++) {
                JSONObject forecastObj = forecastList.getJSONObject(i);
                WeatherData weatherData = new WeatherData();

                // Parse weather data for each forecast
                weatherData.formatData(forecastObj.toString());

                // Add the parsed weather data to the forecasts vector
                forecasts.add(weatherData);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void showForecasts(String forecastData)
    {
        try {

            formatData(forecastData);

            for (int i = 0; i < forecasts.size(); i++) {
                System.out.println("Forecast for Day " + (i + 1) + ":");
                forecasts.get(i).showWeather();
                System.out.println("---------------------------------------------------------------------------------------"); // Add a new line for separation between forecasts
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
