package CacheStorage_TextFiles;

import BusinessLogic.CacheManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Vector;

public class CacheStorage_TextFiles extends CacheManager{
    private   String weatherReportsfile = "weatherReports.txt";
    private   String airReportsfile = "airReports.txt";
    private   String forecastReportsfile = "forecastReports.txt";
    private   String locationsfile = "locations.txt";

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

    private String getFileName(String reportType) throws Exception
    {
        switch (reportType)
        {
            case "Weather":
                return weatherReportsfile;

            case "Air":
                return airReportsfile;

            case "Forecast":
                return forecastReportsfile;

            default:
                throw new Exception("Invalid report type: " + reportType);
        }
    }

    public String fetchReport(double latitude, double longitude,String reportType) throws Exception{

        String filename = getFileName(reportType);

        File file = openFile(filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
        return null; // Return null if no matching data found
    }

    public boolean storeReport(double latitude, double longitude, String reportType,String report) throws Exception
    {
        String filename = getFileName(reportType);

        File file = openFile(filename);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true)))
        {
            writer.write(latitude + "," + longitude + "," + report);
            writer.newLine();
            System.out.println("Report written to " + filename + " successfully.");
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public boolean storeLocation(String locationDetails, Boolean current, Boolean locationExists) throws Exception {

        try {
            JSONArray jsonArray = new JSONArray(locationDetails);
            JSONObject jsonLocation = jsonArray.getJSONObject(0);
            String cityName = jsonLocation.getString("name");
            double latitude = jsonLocation.getDouble("lat");
            double longitude = jsonLocation.getDouble("lon");
            String country = jsonLocation.getString("country");
            String state = jsonLocation.optString("state", null);

            File file = openFile(locationsfile);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder fileContents = new StringBuilder();
            String line;
            boolean locationExists1 = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(cityName) && line.contains(String.valueOf(latitude)) && line.contains(String.valueOf(longitude))) {
                    if (locationExists) {
                        // Append ", current" at the end of the existing line
                        line += ", current";
                    }
                    locationExists1 = true;
                }
                fileContents.append(line).append("\n");
            }
            reader.close();

            if (!locationExists1) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                StringBuilder locationBuilder = new StringBuilder();
                locationBuilder.append("City: ").append(cityName).append(", Country: ").append(country).append(", Latitude: ").append(latitude).append(", Longitude: ").append(longitude);
                if (state != null && !state.isEmpty()) {
                    locationBuilder.append(", State: ").append(state);
                }
                if (current != null && current) {
                    locationBuilder.append(", current");
                }
                writer.write(locationBuilder.toString());
                writer.newLine();
                writer.close();
                System.out.println("Location details written successfully.");
            } else {
                // Rewrite the file with updated contents
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(fileContents.toString());
                writer.close();
                System.out.println("Location details updated successfully.");
            }
            return true;

        } catch (Exception e) {
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

    public String fetchCurrentLocation() throws Exception {
        try {
            File file = openFile(locationsfile);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder currentLocation = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.contains("current")) {
                    currentLocation.append(line).append("\n");
                }
            }
            reader.close();

            // Check if currentLocation is empty
            if (currentLocation.length() == 0) {
                return null; // Return null if no current location found
            } else {
                return currentLocation.toString();
            }
        } catch (Exception e) {
            throw e;
        }
    }



    /*
    public boolean overWriteReports(Vector<String> coordinates, Vector<String> reports, String reportType) throws Exception
    {

        String filename = getFileName(reportType);

        File file = openFile(filename);
        file.delete();

        double latitude, longitude;
        String[] coordinates_parts;

        try
        {
            for(int i=0; i<reports.size(); i++)
            {
                coordinates_parts = coordinates.get(i).split(",",2);
                latitude = Double.parseDouble(coordinates_parts[0]);
                longitude = Double.parseDouble(coordinates_parts[1]);
                storeReport(latitude,longitude,filename,reports.get(i));
            }
            return true;
        }
        catch (Exception e)
        {
            throw  e;
        }


    }
    */

}
