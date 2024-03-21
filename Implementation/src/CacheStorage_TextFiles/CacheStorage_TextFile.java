package CacheStorage_TextFiles;
import BusinessLogic.src.CacheManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Vector;

public class CacheStorage_TextFile {
    private   String weatherReportsfile = "src/CacheStorage_TextFiles/weatherReports.txt";
    private   String airReportsfile = "src/CacheStorage_TextFiles/airReports.txt";
    private   String forecastReportsfile = "src/CacheStorage_TextFiles/forecastReports.txt";
    private   String locationsfile = "src/CacheStorage_TextFiles/locations.txt";

    private File openFile(String filename) throws Exception
    {
        File file = new File(filename);

            try {
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        System.out.println("File created: " + filename);
                    } else {
                        System.err.println("Failed to create file: " + filename);
                    }
                }
                return file;
            } catch (Exception e) {
                throw e;
            }
    }

    private String fetchReport(double latitude, double longitude,String defaultMsg,String filename) throws Exception{
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                double lat = Double.parseDouble(parts[0]);
                double lon = Double.parseDouble(parts[1]);
                String weatherReport = parts[2];

                // Check if latitude and longitude match
                if (lat == latitude && lon == longitude) {
                    return weatherReport;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return defaultMsg; // Return null if no matching data found
    }

    private boolean storeReport(double latitude, double longitude, String WeatherReport,String filename) throws Exception
    {

        File file = openFile(filename);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true)))
        {
            writer.write(latitude + "," + longitude + "," + WeatherReport);
            writer.newLine();
            System.out.println("Report written to " + filename + " successfully.");
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    public boolean storeWeatherReport(double latitude, double longitude, String WeatherReport) throws Exception
    {
        return storeReport(latitude,longitude,WeatherReport,weatherReportsfile);
    }

    public boolean storeAirReport(double latitude, double longitude, String airReport) throws Exception
    {
        return storeReport(latitude,longitude,airReport,airReportsfile);
    }

    public boolean storeForecast(double latitude, double longitude, String forecast) throws Exception
    {
        return storeReport(latitude,longitude,forecast,forecastReportsfile);
    }

    public String fetchWeatherReport(double latitude, double longitude) throws Exception
    {
        return fetchReport(latitude,longitude,"No weather report found",weatherReportsfile);
    }

    public String fetchAirReport(double latitude, double longitude) throws Exception
    {
        return fetchReport(latitude,longitude,"No air report found",airReportsfile);
    }

    public String fetchForecast(double latitude, double longitude) throws Exception
    {
        return fetchReport(latitude,longitude,"No forecast report found",forecastReportsfile);
    }

    public boolean storeLocation(String locationDetails) throws Exception
    {
        try{
            JSONArray jsonArray = new JSONArray(locationDetails);
            JSONObject jsonLocation = jsonArray.getJSONObject(0);
            String cityName = jsonLocation.getString("name");
            double latitude = jsonLocation.getDouble("lat");
            double longitude = jsonLocation.getDouble("lon");
            String country = jsonLocation.getString("country");
            String state = jsonLocation.optString("state", null);

            File file = openFile(locationsfile);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(cityName + "," + latitude + "," + longitude + "," + country + "," + state);
            writer.newLine();
            System.out.println("Location details written to " + locationDetails + " successfully.");
            writer.close();
            return true;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public Vector<String> fetchStoredLocations() throws Exception
    {
        Vector<String> locations = new Vector<>();
        String line;
        try
        {
            File file = openFile(locationsfile);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null)
            {
            locations.add(line);
            }
            return locations;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public boolean overWriteWeatherReports(Vector<String> coordinates, Vector<String> reports) throws Exception
    {
        File file = openFile(weatherReportsfile);

        double latitude, longitude;
        String[] coordinates_parts;

        try
        {
            for(int i=0; i<reports.size(); i++)
            {
                coordinates_parts = coordinates.get(i).split(",",2);
                latitude = Double.parseDouble(coordinates_parts[0]);
                longitude = Double.parseDouble(coordinates_parts[1]);
                storeWeatherReport(latitude,longitude,reports.get(i));
            }
            return true;
        }
        catch (Exception e)
        {
            throw  e;
        }


    }

}
