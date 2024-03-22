package BusinessLogic.src;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Vector;


public class LocationManager {

  abstract static public class LocationManagerInterface{
        abstract public boolean addLocation(double latitude, double longitude) throws Exception;
        abstract public Vector<String> fetchStoredLocations() throws Exception;
        abstract  public  double[] convertToCoordinates(String location) throws Exception;
        abstract  public double[] verifyCoordinates(double latitude, double longitude) throws Exception;

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

        public double[] convertToCoordinates(String Location) throws Exception {
            try {
                String encodedLocation = URLEncoder.encode(Location);

                String locationDetails = api.directGeoCoding(encodedLocation);

                // Check if locationDetails is null or empty before proceeding
                if (locationDetails == null || locationDetails.isEmpty()) {
                    throw new Exception("Empty location details returned");
                }

                JSONArray jsonArray = new JSONArray(locationDetails);

                // Check if jsonArray is empty before proceeding
                if (jsonArray.isEmpty()) {
                    throw new Exception("Empty location details returned");
                }

                JSONObject jsonLocation = jsonArray.getJSONObject(0);
                double latitude = jsonLocation.getDouble("lat");
                double longitude = jsonLocation.getDouble("lon");

                // Return the latitude and longitude as an array of doubles
                return new double[]{latitude, longitude};

            } catch (Exception e) {
                throw e;
            }
        }

        public double[] verifyCoordinates(double latitude, double longitude) throws Exception {
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

                // Assuming the locationDetails contains a single JSON object
                JSONObject jsonLocation = jsonArray.getJSONObject(0);

                // Update latitude and longitude based on the reverse geocoded values
                latitude = jsonLocation.getDouble("lat");
                longitude = jsonLocation.getDouble("lon");

                // Return the updated latitude and longitude as a new array of doubles
                return new double[]{latitude, longitude};

            } catch (Exception e) {
                throw e;
            }
        }
    }


}
