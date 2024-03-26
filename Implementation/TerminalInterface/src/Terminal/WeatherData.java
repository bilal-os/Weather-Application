package Terminal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class WeatherData {

    public String main;
    public String description;
    public String icon;
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;
    public int sea_level;
    public int grnd_level;
    public int visibility;
    public long dt;
    public int timezone;
    public String name;
    public double speed;
    public int deg;
    public double gust;
    public double rainh1;
    public double snowh1;
    public int cloudsall;

    public String country;
    public long sunrise;
    public long sunset;

    public WeatherData() {
        main = "";
        description = "";
        icon = "";
        temp = 0.0;
        feels_like = 0.0;
        temp_min = 0.0;
        temp_max = 0.0;
        pressure = 0;
        humidity = 0;
        sea_level = 0;
        grnd_level = 0;
        visibility = 0;
        dt = 0;
        timezone = 0;
        name = "";
        speed = 0.0;
        deg = 0;
        gust = 0.0;
        rainh1 = 0.0;
        snowh1 = 0.0;
        cloudsall = 0;
        country = "";
        sunrise = 0;
        sunset = 0;
    }

    public void formatData(String weatherReport) throws Exception {
        try {
            JSONObject report = new JSONObject(weatherReport);

            if(report != null) {

                JSONArray weatherArray = report.getJSONArray("weather");
                JSONObject weatherObj = weatherArray.getJSONObject(0);
                if (weatherObj != null) {
                    main = weatherObj.optString("main");
                    description = weatherObj.optString("description");
                    icon = weatherObj.optString("icon");
                }

                // Initialize Main
                JSONObject mainObj = report.optJSONObject("main");
                if (mainObj != null) {
                    temp = mainObj.optDouble("temp");
                    feels_like = mainObj.optDouble("feels_like");
                    temp_min = mainObj.optDouble("temp_min");
                    temp_max = mainObj.optDouble("temp_max");
                    pressure = mainObj.optInt("pressure");
                    humidity = mainObj.optInt("humidity");
                    sea_level = mainObj.optInt("sea_level");
                    grnd_level = mainObj.optInt("grnd_level");
                }

                visibility = report.optInt("visibility");

                // Initialize Wind
                JSONObject windObj = report.optJSONObject("wind");
                if (windObj != null) {
                   speed = windObj.optDouble("speed");
                    deg = windObj.optInt("deg");
                    gust = windObj.optDouble("gust");
                }

                // Initialize Rain
                JSONObject rainObj = report.optJSONObject("rain");
                if (rainObj != null) {
                    rainh1 = rainObj.optDouble("1h");
                }

                // Initialize Snow
                JSONObject snowObj = report.optJSONObject("snow");
                if (snowObj != null) {
                    snowh1 = snowObj.optDouble("1h");
                }

                // Initialize Clouds
                JSONObject cloudsObj = report.optJSONObject("clouds");
                if (cloudsObj != null) {
                    cloudsall = cloudsObj.optInt("all");
                }

                dt = report.optLong("dt");

                // Initialize Sys
                JSONObject sysObj = report.optJSONObject("sys");
                if (sysObj != null) {
                    country = sysObj.optString("country");
                    sunrise = sysObj.optLong("sunrise");
                    sunset = sysObj.optLong("sunset");
                }

                timezone = report.optInt("timezone");
                name = report.optString("name");
            } } catch (Exception e) {
            throw e;
        }
    }


    public String getReadableDate() {
        Date date = new Date(dt * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}
