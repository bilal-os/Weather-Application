import BusinessLogic.CacheManager;
import BusinessLogic.DataManager;
import BusinessLogic.LocationManager;
import BusinessLogic.NotificationManager;
import CacheStorage_DataBase.CacheStorage_Database;
import CacheStorage_TextFiles.CacheStorage_TextFiles;
import Terminal.TerminalInterface;
import org.example.MicroService;

public class Main {
    public static void main(String[] args) {

        try {
            if (args.length == 0) {
                // Set default arguments
                args = new String[]{"Database", "GUI"};
            }
            CacheManager cacheManager=null;

            if(args[0].equalsIgnoreCase("Database"))
            {
                cacheManager = new CacheStorage_Database();
            }

            else if(args[0].equalsIgnoreCase("TextFile"))
            {
                cacheManager = new CacheStorage_TextFiles();
            }
            if(args[1].equalsIgnoreCase("Terminal")) {
                NotificationManager notificationManager = new NotificationManager(cacheManager);
                DataManager.DataManagerInterface dataManagerInterface = new DataManager.Data_Manager(cacheManager);
                LocationManager.LocationManagerInterface locationManagerInterface = new LocationManager.Location_Manager(cacheManager);
                TerminalInterface terminalInterface = new TerminalInterface(dataManagerInterface, locationManagerInterface, notificationManager);

                terminalInterface.showTerminal();
            } else if (args[1].equalsIgnoreCase("GUI")) {
                MicroService microService = new MicroService(cacheManager);
                microService.startService();
                System.out.println("Micro Service Started");

                String command = "java -jar TomCatServer.jar";
                ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
                Process process = processBuilder.start();

                System.out.println("Application deployed at localhost:8080");
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
