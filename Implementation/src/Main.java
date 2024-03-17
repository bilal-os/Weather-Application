import BusinessLogic.src.DataManager;

import java.net.URLEncoder;
import java.util.Vector;

import BusinessLogic.src.GeoCoding;
import BusinessLogic.src.LocationManager;
import CacheStorage_DataBase.CacheStorage;

public class Main {
    public static void main(String[] args) {

        CacheStorage cacheStorage = new CacheStorage();
        LocationManager.Location_Manager locationManager = new LocationManager.Location_Manager(cacheStorage);
        System.out.println(locationManager.verifyCoordinates(1,2));

    }
}