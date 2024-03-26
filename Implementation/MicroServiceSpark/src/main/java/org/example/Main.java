package org.example;
import static spark.Spark.*;
import BusinessLogic.*;
import CacheStorage_DataBase.CacheStorage;
import CacheStorage_TextFiles.CacheStorage_TextFile;


public class Main {
    public static void main(String[] args) {
        enableCORS("*","*","*");
        try {
            CacheManager cacheManager = new CacheStorage_TextFile();
            DataManager.DataManagerInterface dataManagerInterface = new DataManager.Data_Manager(cacheManager);

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