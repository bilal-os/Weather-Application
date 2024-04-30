package org.example;
import static spark.Spark.*;
import BusinessLogic.CacheManager;
import BusinessLogic.DataManager;
import BusinessLogic.LocationManager;

import java.util.Vector;

public class MicroService {

    CacheManager cacheManager;

    public MicroService(CacheManager cacheManager)
    {
        this.cacheManager=cacheManager;
    }

    public void startService() {
        enableCORS("*","*","*");
        try {
            DataManager.DataManagerInterface dataManagerInterface = new DataManager.Data_Manager(cacheManager);
            LocationManager.LocationManagerInterface locationManagerInterface = new LocationManager.Location_Manager(cacheManager);

            get("/currentWeather", (req, res) -> {
                double latitude = Double.parseDouble(req.queryParams("lat"));
                double longitude = Double.parseDouble(req.queryParams("lon"));
                return dataManagerInterface.fetchReport(latitude,longitude,"Weather");

            });

            get("/air", (req, res) -> {
                double latitude = Double.parseDouble(req.queryParams("lat"));
                double longitude = Double.parseDouble(req.queryParams("lon"));
                return dataManagerInterface.fetchReport(latitude,longitude,"Air");

            });

            get("/forecast", (req, res) -> {
                double latitude = Double.parseDouble(req.queryParams("lat"));
                double longitude = Double.parseDouble(req.queryParams("lon"));
                return dataManagerInterface.fetchReport(latitude,longitude,"Forecast");

            });
            get("/locations", (req, res) -> {
                Vector<String> locations = locationManagerInterface.fetchStoredLocations();
                // Create a StringBuilder to efficiently build the final string
                StringBuilder combinedString = new StringBuilder();

                // Iterate over each string in the vector and append it to the StringBuilder
                for (String location : locations) {
                    combinedString.append(location).append("\n"); // Add a new line after each location
                }

                // Convert the StringBuilder to a String
                String finalString = combinedString.toString();

                // Set the content type to plain text
                res.type("text/plain");

                // Return the final combined string
                return finalString;
            });

            get("/addlocation",(req,res) -> {
                double latitude = Double.parseDouble(req.queryParams("lat"));
                double longitude = Double.parseDouble(req.queryParams("lon"));
                boolean current = Boolean.parseBoolean(req.queryParams("curr"));
               return locationManagerInterface.addLocation(latitude,longitude,current);
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }
    // Enables CORS on requests. This method is an initialization method and should be called once.
    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}