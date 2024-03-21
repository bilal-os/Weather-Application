package BusinessLogic.src;

import java.sql.SQLException;
import java.util.Vector;

abstract public class CacheManager {

   abstract public boolean storeReport(double latitude, double longitude, String reportType, String report) throws SQLException;

   abstract public String fetchReport(double latitude, double longitude, String reportType) throws SQLException;

   abstract public boolean storeLocation(String locationDetails) throws Exception;

   abstract public Vector<String> fetchStoredLocations() throws Exception;


}
