package BusinessLogic.src;

import java.sql.SQLException;
import java.util.Vector;

abstract public class CacheManager {

   abstract public boolean storeReport(double latitude, double longitude, String reportType, String report) throws Exception;

   abstract public String fetchReport(double latitude, double longitude, String reportType) throws Exception;

   abstract public boolean storeLocation(String locationDetails, Boolean current) throws Exception;

   abstract public Vector<String> fetchStoredLocations() throws Exception;

   abstract public String fetchCurrentLocation() throws Exception;


}
