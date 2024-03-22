import BusinessLogic.src.*;

import java.net.URLEncoder;
import java.util.Vector;

import CacheStorage_DataBase.CacheStorage;
import java.sql.*;
import CacheStorage_TextFiles.CacheStorage_TextFile;
import FrontEnd_Terminal_Interface.TerminalInterface;

public class Main {
    public static void main(String[] args) {

        try {
            CacheManager cacheManager = new CacheStorage();
            DataManager.DataManagerInterface dataManagerInterface = new DataManager.Data_Manager(cacheManager);
            LocationManager.LocationManagerInterface locationManagerInterface = new LocationManager.Location_Manager(cacheManager);
            TerminalInterface terminalInterface = new TerminalInterface(dataManagerInterface,locationManagerInterface);

            terminalInterface.showTerminal();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }



    }
}
