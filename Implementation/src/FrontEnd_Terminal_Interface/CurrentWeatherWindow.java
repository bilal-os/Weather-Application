package FrontEnd_Terminal_Interface;

public class CurrentWeatherWindow {
    private WeatherData weatherReport;

    public CurrentWeatherWindow()
    {
        weatherReport = new WeatherData();
    }

    public void showCurrentWeather(String weatherReport)
    {
        try {
            this.weatherReport.formatData(weatherReport);
            this.weatherReport.showWeather();
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
