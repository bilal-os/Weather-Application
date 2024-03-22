package FrontEnd_Terminal_Interface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CurrentWeatherWindow {
    private WeatherData weatherData;

    public CurrentWeatherWindow()
    {
        weatherData = new WeatherData();
    }

    public void showWeather(String weatherReport)
    {
        try {
            weatherData.formatData(weatherReport);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Report Date: " + dateFormat.format(new Date(weatherData.dt * 1000)));

            // Print basic weather information
            System.out.println("\nWeather Information for " + weatherData.name);
            System.out.println("Weather conditions: " + weatherData.description);
            System.out.println("Temperature: " + weatherData.temp + "°F");
            System.out.println("Feels like: " + weatherData.feels_like + "°F");
            System.out.println("Min temperature: " + weatherData.temp_min + "°F");
            System.out.println("Max temperature: " + weatherData.temp_max + "°F");
            System.out.println("Pressure: " + weatherData.pressure + " hPa");
            System.out.println("Humidity: " + weatherData.humidity + "%");
            System.out.println("Sea level: " + weatherData.sea_level + " hPa");
            System.out.println("Ground level: " + weatherData.grnd_level + " hPa");
            System.out.println("Cloudiness: " + weatherData.cloudsall + "%");
            System.out.println("Main: " + weatherData.main);
            System.out.println("Icon: " + weatherData.icon);

            // Print additional weather information
            if (weatherData.visibility > 0) {
                System.out.println("Visibility: " + weatherData.visibility + " meters");
            }
            System.out.println("Wind speed: " + weatherData.speed + " m/s");
            System.out.println("Wind direction: " + weatherData.deg + "°");
            System.out.println("Wind gust: " + weatherData.gust + "°");

            // Interpretation based on temperature and humidity
            if (weatherData.temp > 25) {
                System.out.println("It's quite warm today.");
            } else if (weatherData.temp < 10) {
                System.out.println("It's chilly outside.");
            } else {
                System.out.println("The temperature is moderate.");
            }

            if (weatherData.humidity > 70) {
                System.out.println("It's humid.");
            } else if (weatherData.humidity < 30) {
                System.out.println("The air is dry.");
            }

            if (weatherData.speed > 10) {
                System.out.println("It's windy today.");
            }

            // Print rainfall and snowfall
            if (weatherData.rainh1 > 0) {
                System.out.println("Rainfall in the last hour: " + weatherData.rainh1 + " mm");
            }
            if (weatherData.snowh1 > 0) {
                System.out.println("Snowfall in the last hour: " + weatherData.snowh1 + " mm");
            }

            // Print additional information
            System.out.println("Timezone: GMT" + (weatherData.timezone >= 0 ? "+" : "") + (weatherData.timezone / 3600));
            if (weatherData.sunrise > 0 && weatherData.sunset > 0) {
                System.out.println("Sunrise time: " + new Date(weatherData.sunrise * 1000));
                System.out.println("Sunset time: " + new Date(weatherData.sunset * 1000));
            }
            System.out.println("Last update time: " + new Date(weatherData.dt * 1000));
            System.out.println("Press '1' to exit.");

            // Handle user input
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("1")) {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: "+ e.getMessage());
        }

    }


}
