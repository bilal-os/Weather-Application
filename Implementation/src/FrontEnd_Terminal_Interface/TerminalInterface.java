package FrontEnd_Terminal_Interface;

import BusinessLogic.src.DataManager;
import BusinessLogic.src.LocationManager;
import BusinessLogic.src.NotificationManager;

import java.util.Scanner;
import java.util.Vector;

public class TerminalInterface {

    private Vector<String> storedLocations ;

    private  DataManager.DataManagerInterface dataManagerInterface;
    private  LocationManager.LocationManagerInterface locationManagerInterface;
    private  CurrentWeatherWindow currentWeatherWindow;
    private ForeCastWindow forecastWindow;
    private AirReportWindow airReportWindow;

    private NotificationManager notificationManager;


    String notification;

    public TerminalInterface(DataManager.DataManagerInterface dataManagerInterface, LocationManager.LocationManagerInterface locationManagerInterface, NotificationManager notificationManager) {

        try{
        this.dataManagerInterface = dataManagerInterface;
        this.locationManagerInterface = locationManagerInterface;
        this.currentWeatherWindow = new CurrentWeatherWindow();
        this.forecastWindow = new ForeCastWindow();
        this.airReportWindow = new AirReportWindow();
        this.notificationManager=notificationManager;
        notification=null;
        storedLocations = locationManagerInterface.fetchStoredLocations();}
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void showTerminal() throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            boolean exit = false;
            while (!exit) {
                if(notification!=null)
                {
                    System.out.println(notification);
                }

                System.out.println("\n                         1: Show Current Weather of a location");
                System.out.println("                         2: Show Air Report of a location");
                System.out.println("                         3: Show Forecast Report of a location");
                System.out.println("                         4: to add a location");
                System.out.println("                         5: show stored locations");
                System.out.println("                         6: to set a current location");
                System.out.println("                         7: to enable notification");
                System.out.println("                         8: To exit\n");
                System.out.println("Select by entering the specified number(1 to 8): ");
                int option = scanner.nextInt();
                switch (option)
                {
                    case 1, 2, 3 -> showReport(scanner, option);
                    case 4 -> addLocation(scanner,false);
                    case 5 -> showStoredLocations();
                    case 6 -> addLocation(scanner,true);
                    case 7 -> enableNotification();
                    case 8 -> exit = true;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    public void enableNotification()
    {
        try {
            System.out.println(notificationManager.enableNotification());
            notification=notificationManager.raiseNotification();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }

    private void showReport(Scanner scanner, int option) {
        String reportType = switch (option) {
            case 1 -> "Weather";
            case 2 -> "Air";
            case 3 -> "Forecast";
            default -> throw new IllegalArgumentException("Invalid report type.");
        };
        System.out.println(">>Press 1 to Enter City\n>>Press 2 to Enter Coordinates");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> processReport(reportType, getCoordinatesByCity(scanner));
            case 2 -> processReport(reportType, getCoordinatesByCoordinates(scanner));
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private double[] getCoordinatesByCity(Scanner scanner) {
        System.out.println("Enter the city name: ");
        String city = scanner.next();
        try {
            return locationManagerInterface.convertToCoordinates(city);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private double[] getCoordinatesByCoordinates(Scanner scanner) {
        System.out.println("Enter the latitude: ");
        double latitude = scanner.nextDouble();
        System.out.println("Enter the longitude: ");
        double longitude = scanner.nextDouble();
        try {
            return locationManagerInterface.verifyCoordinates(latitude, longitude);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private void processReport(String reportType, double[] coordinates) {
        if (coordinates == null) return;
        try {
            String report = dataManagerInterface.fetchReport(coordinates[0], coordinates[1], reportType);
            switch (reportType) {
                case "Weather" -> currentWeatherWindow.showWeather(report);
                case "Forecast" -> forecastWindow.showForecasts(report);
                case "Air" -> airReportWindow.showAirReport(report);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addLocation(Scanner scanner,Boolean current) {
        System.out.println(">>Press 1 to Enter City\n>>Press 2 to Enter Coordinates");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                double[] coordinates = getCoordinatesByCity(scanner);
                if (coordinates != null) {
                    try {
                            locationManagerInterface.addLocation(coordinates[0], coordinates[1],current);
                            System.out.println("Location added successfully");
                            storedLocations = locationManagerInterface.fetchStoredLocations();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            case 2 -> {
                double[] coordinates = getCoordinatesByCoordinates(scanner);
                if (coordinates != null) {
                    try {
                        locationManagerInterface.addLocation(coordinates[0], coordinates[1],false);
                        System.out.println("Location added successfully");
                        storedLocations=locationManagerInterface.fetchStoredLocations();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            default -> System.out.println("Invalid option. Please try again.");
        }

        System.out.println("\n>>Press '1' to get back to the main menu.");

        // Handle user input
        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            String userInput = scanner1.nextLine();
            if (userInput.equalsIgnoreCase("1")) {
                break;
            }
        }
    }


    public void showStoredLocations()
    {
        System.out.println("    Stored Locations: ");
        for (String storedLocation : storedLocations) {
            System.out.println(storedLocation);
        }
        System.out.println("\n>>Press '1' to get back to the main menu.");

        // Handle user input
        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            String userInput = scanner1.nextLine();
            if (userInput.equalsIgnoreCase("1")) {
                break;
            }
        }
    }



}
