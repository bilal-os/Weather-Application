import BusinessLogic.src.*;

import java.net.URLEncoder;
import java.util.Vector;

import CacheStorage_DataBase.CacheStorage;
import java.sql.*;
import CacheStorage_TextFiles.CacheStorage_TextFile;
public class Main {
    public static void main(String[] args) {

        try {

            GeoCoding geo = new GeoCoding();

            CacheManager cacheManager = new CacheStorage();
            CacheStorage_TextFile cache = new CacheStorage_TextFile();
            DataManager.Data_Manager dataManager = new DataManager.Data_Manager(cacheManager);

           String data = dataManager.fetchWeatherReport(51.5074,-0.1278);
           String data1 = dataManager.fetchAirReport(51.5074,-0.1278);
           String data2 = dataManager.fetchForecast(51.5074,-0.1278);
           String data3 = geo.reverseGeoCoding(51.5074,-0.1278);
           cache.storeLocation(data3);
           cache.storeLocation(data3);
            Vector<String> location = new Vector<>();
            location = cache.fetchStoredLocations();

            for(String location1: location)
            {
                System.out.println(location1);

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }



    }
}
