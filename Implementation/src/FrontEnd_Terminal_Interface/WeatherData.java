package FrontEnd_Terminal_Interface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.Scanner;

public class WeatherData {

    private Weather weather;
    private Main main;
    private int visibility;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private String name;


    private static class Weather {
        private String main;
        private String description;
        private String icon;
    }

    private static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;
    }

    private static class Wind {
        private double speed;
        private int deg;
        private double gust;
    }

    private static class Rain {
        private double h1;
    }

    private static class Snow {
        private double h1;
    }

    private static class Clouds {
        private int all;
    }

    private static class Sys {
        private String country;
        private long sunrise;
        private long sunset;
    }


    public void formatData(String weatherReport) throws Exception {
        try {
            JSONObject report = new JSONObject(weatherReport);

            if(report != null) {

                JSONArray weatherArray = report.getJSONArray("weather");
                JSONObject weatherObj = weatherArray.getJSONObject(0);
                if (weatherObj != null) {
                    weather = new Weather();
                    weather.main = weatherObj.optString("main");
                    weather.description = weatherObj.optString("description");
                    weather.icon = weatherObj.optString("icon");
                }

                // Initialize Main
                JSONObject mainObj = report.optJSONObject("main");
                if (mainObj != null) {
                    main = new Main();
                    main.temp = mainObj.optDouble("temp");
                    main.feels_like = mainObj.optDouble("feels_like");
                    main.temp_min = mainObj.optDouble("temp_min");
                    main.temp_max = mainObj.optDouble("temp_max");
                    main.pressure = mainObj.optInt("pressure");
                    main.humidity = mainObj.optInt("humidity");
                    main.sea_level = mainObj.optInt("sea_level");
                    main.grnd_level = mainObj.optInt("grnd_level");
                }

                visibility = report.optInt("visibility");

                // Initialize Wind
                JSONObject windObj = report.optJSONObject("wind");
                if (windObj != null) {
                    wind = new Wind();
                    wind.speed = windObj.optDouble("speed");
                    wind.deg = windObj.optInt("deg");
                    wind.gust = windObj.optDouble("gust");
                }

                // Initialize Rain
                JSONObject rainObj = report.optJSONObject("rain");
                if (rainObj != null) {
                    rain = new Rain();
                    rain.h1 = rainObj.optDouble("1h");
                }

                // Initialize Snow
                JSONObject snowObj = report.optJSONObject("snow");
                if (snowObj != null) {
                    snow = new Snow();
                    snow.h1 = snowObj.optDouble("1h");
                }

                // Initialize Clouds
                JSONObject cloudsObj = report.optJSONObject("clouds");
                if (cloudsObj != null) {
                    clouds = new Clouds();
                    clouds.all = cloudsObj.optInt("all");
                }

                dt = report.optLong("dt");

                // Initialize Sys
                JSONObject sysObj = report.optJSONObject("sys");
                if (sysObj != null) {
                    sys = new Sys();
                    sys.country = sysObj.optString("country");
                    sys.sunrise = sysObj.optLong("sunrise");
                    sys.sunset = sysObj.optLong("sunset");
                }

                timezone = report.optInt("timezone");
                name = report.optString("name");
            } } catch (Exception e) {
            throw e;
        }
    }

    public void showWeather() {
        try {
            System.out.println("Weather Information for " + name);

            if (weather != null) {
                System.out.println("Weather conditions: " + weather.description);
            }

            if (main != null) {
                System.out.println("Temperature: " + main.temp + "°F");
                System.out.println("Feels like: " + main.feels_like + "°F");
                System.out.println("Min temperature: " + main.temp_min + "°F");
                System.out.println("Max temperature: " + main.temp_max + "°F");
                System.out.println("Pressure: " + main.pressure + " hPa");
                System.out.println("Humidity: " + main.humidity + "%");
            }

            if (visibility > 0) {
                System.out.println("Visibility: " + visibility + " meters");
            }

            if (wind != null) {
                System.out.println("Wind speed: " + wind.speed + " m/s");
                System.out.println("Wind direction: " + wind.deg + "°");
                System.out.println("Wind gust: " + wind.gust + "°");
            }

            // Interpretation based on temperature and humidity
            if (main != null && main.temp != 0) {
                if (main.temp > 25) {
                    System.out.println("It's quite warm today.");
                } else if (main.temp < 10) {
                    System.out.println("It's chilly outside.");
                } else {
                    System.out.println("The temperature is moderate.");
                }
            }

            if (main != null && main.humidity != 0) {
                if (main.humidity > 70) {
                    System.out.println("It's humid.");
                } else if (main.humidity < 30) {
                    System.out.println("The air is dry.");
                }
            }

            if (wind != null && wind.speed > 0) {
                if (wind.speed > 10) {
                    System.out.println("It's windy today.");
                }
            }

            // Print rain if available
            if (rain != null && rain.h1 > 0) {
                System.out.println("Rainfall in the last hour: " + rain.h1 + " mm");
            }

            // Print snow if available
            if (snow != null && snow.h1 > 0) {
                System.out.println("Snowfall in the last hour: " + snow.h1 + " mm");
            }

            // Additional information
            System.out.println("Timezone: GMT" + (timezone >= 0 ? "+" : "") + (timezone / 3600));
            if (sys != null) {
                System.out.println("Sunrise time: " + new Date(sys.sunrise * 1000));
                System.out.println("Sunset time: " + new Date(sys.sunset * 1000));
            }
            System.out.println("Last update time: " + new Date(dt * 1000));
            System.out.println("Press '1' to exit.");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("1")) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
