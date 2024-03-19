package BusinessLogic.src;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Vector;

public class LocationManager {

  abstract static public class LocationManagerInterface{
        abstract public String addLocation(double latitude, double longitude);
        abstract public Vector<String> fetchStoredLocations();
        abstract  public  String convertToCoordinates(String location);
        abstract  public boolean verifyCoordinates(double latitude, double longitude);

  }

    static public class Location_Manager extends LocationManagerInterface{

      private DataManager.Data_Manager dataManager;
      private CacheManager cacheManager;
      private GeoCoding geoCoding;

      public Location_Manager(CacheManager cacheManager)
      {
        this.cacheManager=cacheManager;
        dataManager = new DataManager.Data_Manager(cacheManager);
        geoCoding = new GeoCoding();
      }
      public String addLocation(double latitude, double longitude)
      {
          String weatherReport, airReport, foreCast, locationDetails;
          try {
          weatherReport = dataManager.fetchWeatherReport(latitude,longitude);
          airReport = dataManager.fetchAirReport(latitude,longitude);
          foreCast = dataManager.fetchForecast(latitude,longitude);
          locationDetails = geoCoding.reverseGeoCoding(latitude,longitude);



              cacheManager.storeWeatherReport(latitude, longitude, weatherReport);
              cacheManager.storeAirReport(latitude, longitude, airReport);
              cacheManager.storeForecast(latitude, longitude, foreCast);
              cacheManager.storeLocation(locationDetails);

              return "Location Stored";
          }
          catch (Exception e)
          {
              System.out.println("Exception occurred: " + e.getMessage());
              return null;
          }

      }
      public Vector<String> fetchStoredLocations()
      {
          try {
              return cacheManager.fetchStoredLocations();
          }
          catch (Exception e)
          {
              System.out.println("Exception occurred: " + e.getMessage());
              return null;
          }
      }

      public String convertToCoordinates(String Location)  {
            StringBuilder coordinates = new StringBuilder();

            String encodedLocation = URLEncoder.encode(Location);

            try {
                String locationDetails = geoCoding.directGeoCoding(encodedLocation);

                if (locationDetails.isEmpty()) {
                    return "Incorrect Location";
                }

                JSONArray jsonArray = new JSONArray(locationDetails);
                if (jsonArray.isEmpty()) {
                    return "Incorrect Location";
                }

                JSONObject jsonLocation = jsonArray.getJSONObject(0);
                double latitude = jsonLocation.getDouble("lat");
                double longitude = jsonLocation.getDouble("lon");

                coordinates.append(latitude);
                coordinates.append(", ");
                coordinates.append(longitude);

                return coordinates.toString();
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
                return null;
            }
        }

      public boolean verifyCoordinates(double latitude, double longitude)
      {
          String locationDetails;
          try{
           locationDetails = geoCoding.reverseGeoCoding(latitude, longitude);}
          catch (Exception e)
          {
              System.out.println("Exception occurred: " + e.getMessage());
              return  false;
          }

          if(locationDetails.equals("Error"))
          {
              return false;
          }

          else return !locationDetails.equals("[]");
      }

    }


}
