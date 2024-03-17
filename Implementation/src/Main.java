import BusinessLogic.src.CacheManager;
import BusinessLogic.src.DataManager;

import java.net.URLEncoder;
import java.util.Vector;

import BusinessLogic.src.GeoCoding;
import BusinessLogic.src.LocationManager;
import CacheStorage_DataBase.CacheStorage;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            CacheManager cacheManager = new CacheStorage();
            DataManager.Data_Manager dataManager = new DataManager.Data_Manager(cacheManager);

           String data = dataManager.fetchWeatherReport(51.5074,-0.1278);
           String data1 = dataManager.fetchAirReport(51.5074,-0.1278);
           String data2 = dataManager.fetchForecast(51.5074,-0.1278);
           System.out.println(data);
           System.out.println(data1);
           System.out.println(data2);
           dataManager.storeWeatherReport(51.5074,-0.1278,data);



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }
}
