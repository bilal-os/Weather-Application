package BusinessLogic.src;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Vector;


public class LocationManager {

  abstract static public class LocationManagerInterface{
        abstract public boolean addLocation(double latitude, double longitude) throws Exception;
        abstract public Vector<String> fetchStoredLocations() throws Exception;
        abstract  public  String convertToCoordinates(String location) throws Exception;
        abstract  public boolean verifyCoordinates(double latitude, double longitude) throws Exception;

  }
    static public class Location_Manager extends LocationManagerInterface{
      private DataManager.Data_Manager dataManager;
      private CacheManager cacheManager;

      private APIService api;

      public Location_Manager(CacheManager cacheManager)
      {
        this.cacheManager=cacheManager;
        dataManager = new DataManager.Data_Manager(cacheManager);
        api = new APIService();
      }
      public boolean addLocation(double latitude, double longitude) throws Exception
      {
          String weatherReport, airReport, foreCast, locationDetails;
          try {
          weatherReport = dataManager.fetchReport(latitude,longitude,"Weather");
          airReport = dataManager.fetchReport(latitude,longitude,"Air");
          foreCast = dataManager.fetchReport(latitude,longitude,"Forecast");
          locationDetails = api.reverseGeoCoding(latitude,longitude);


              cacheManager.storeReport(latitude, longitude, "Weather",weatherReport);
              cacheManager.storeReport(latitude, longitude, "Air",airReport);
              cacheManager.storeReport(latitude, longitude, "Forecast",foreCast);
              cacheManager.storeLocation(locationDetails);

              return true;
          }
          catch (Exception e)
          {
                throw e;
          }

      }
      public Vector<String> fetchStoredLocations() throws Exception
      {
          try {
              return cacheManager.fetchStoredLocations();
          }
          catch (Exception e)
          {
                throw e;
          }
      }

      public String convertToCoordinates(String Location)  throws Exception{
            StringBuilder coordinates = new StringBuilder();

            String encodedLocation = URLEncoder.encode(Location);

            try {
                String locationDetails = api.directGeoCoding(encodedLocation);

                // Check if locationDetails is null or empty before proceeding
                if (locationDetails == null || locationDetails.isEmpty()) {
                    throw new Exception("Empty location details returned");
                }

                JSONArray jsonArray = new JSONArray(locationDetails);
                if (jsonArray.isEmpty()) {
                    throw new Exception("Empty location details returned");
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
                throw e;
            }
        }

        public boolean verifyCoordinates(double latitude, double longitude) throws Exception {

         try {
             String locationDetails = api.reverseGeoCoding(latitude, longitude);

             // Check if locationDetails is null or empty before proceeding
             if (locationDetails == null || locationDetails.isEmpty()) {
                 throw new Exception("Empty location details returned");
             }

             JSONArray jsonArray = new JSONArray(locationDetails);

             // Check if jsonArray is empty before proceeding
             if (jsonArray.isEmpty()) {
                 throw new Exception("Empty location details returned");
             }

             return true;
         }
         catch (Exception e)
         {
             throw e;
         }
        }
    }


}
