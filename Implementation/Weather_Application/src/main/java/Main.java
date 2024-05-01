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
        Process tomcatProcess = null;
        if(args.length == 0) {
            args = new String[]{"textfile", "gui"};
        }
        try {
            CacheManager cacheManager = null;

            if (args[0].equalsIgnoreCase("Database")) {
                cacheManager = new CacheStorage_Database();
            } else if (args[0].equalsIgnoreCase("TextFile")) {
                cacheManager = new CacheStorage_TextFiles();
            }

            if (args[1].equalsIgnoreCase("Terminal")) {
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

                // Create ProcessBuilder with the command
                ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));

                System.out.println("Application Deployed at https://localhost:8080/");
                // Start the process
                tomcatProcess = processBuilder.start();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Terminate the TomCatServer.jar process if it was started
            if (tomcatProcess != null) {
                tomcatProcess.destroy();
            }
        }
    }
}
