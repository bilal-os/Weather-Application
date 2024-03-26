import BusinessLogic.CacheManager;
import BusinessLogic.DataManager;
import BusinessLogic.LocationManager;
import BusinessLogic.NotificationManager;
import CacheStorage_DataBase.CacheStorage;
import CacheStorage_TextFiles.CacheStorage_TextFile;
import Terminal.TerminalInterface;

public class Main {
    public static void main(String[] args) {

        try {
            CacheManager cacheManager = new CacheStorage();
            NotificationManager notificationManager = new NotificationManager(cacheManager);
            DataManager.DataManagerInterface dataManagerInterface = new DataManager.Data_Manager(cacheManager);
            LocationManager.LocationManagerInterface locationManagerInterface = new LocationManager.Location_Manager(cacheManager);
            TerminalInterface terminalInterface = new TerminalInterface(dataManagerInterface,locationManagerInterface,notificationManager);

            terminalInterface.showTerminal();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
