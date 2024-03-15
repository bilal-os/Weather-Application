import BusinessLogic.src.DataManager;
import java.util.Vector;
import CacheStorage_DataBase.CacheStorage;

public class Main {
    public static void main(String[] args) {

        Vector<String> coordinates = new Vector<>();

        CacheStorage cache = new CacheStorage();

        DataManager.Data_Manager dm2 = new DataManager.Data_Manager(cache);

        // Add coordinates of 5 different places
        coordinates.add("40.7128,-74.0060"); // New York City
        coordinates.add("34.0522,-118.2437"); // Los Angeles
        coordinates.add("51.5074,-0.1278"); // London
        coordinates.add("48.8566,2.3522"); // Paris
        coordinates.add("35.6895,139.6917"); // Tokyo

      //  DataManager.Data_Manager dm = new DataManager.Data_Manager();
        //Vector<String> airReports = dm.fetchForecastReports(coordinates);

        DataManager.Data_Manager dm1 = new DataManager.Data_Manager(cache);
        String weatherData = dm1.fetchForecast(34.0522,-118.2437);

        System.out.println(weatherData);


        // Print each air report
        /*
        System.out.println("Air reports for the provided coordinates:");
        for (String report : airReports) {
            System.out.println(report);
        }
        */


    }
}