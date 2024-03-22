package FrontEnd_Terminal_Interface;

import BusinessLogic.src.DataManager;
import BusinessLogic.src.LocationManager;


import java.util.Vector;
import java.util.Scanner;

public class TerminalInterface {

    private Vector<String> storedLocations;
    private DataManager.DataManagerInterface dataManagerInterface;
    private LocationManager.LocationManagerInterface locationManagerInterface;

    private CurrentWeatherWindow currentWeatherWindow;
    private ForecastWindow forecastWindow;

    public TerminalInterface(DataManager.DataManagerInterface dataManagerInterface, LocationManager.LocationManagerInterface locationManagerInterface) {
        storedLocations = new Vector<>();
        this.dataManagerInterface=dataManagerInterface;
        this.locationManagerInterface=locationManagerInterface;
        currentWeatherWindow = new CurrentWeatherWindow();
        forecastWindow = new ForecastWindow();
    }

    public void showTerminal() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option;

        do {
            System.out.println("Press 1: Show Current Weather of a location");
            System.out.println("Press 2: Show Air Report of a location");
            System.out.println("Press 3: Show Forecast Report of a location");
            System.out.println("Press 4: To exit");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    showReport(scanner,"Weather");
                    break;
                case 2:
                    showReport(scanner,"Air");
                    break;
                case 3:
                    showReport(scanner,"Forecast");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!exit);

        scanner.close();
    }

    public void showReport(Scanner scanner,String reportType) {
        System.out.println("Press 1 to Enter City");
        System.out.println("Press 2 to Enter Coordinates");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                showReportByCity(scanner,reportType);
                break;
            case 2:
                showReportByCoordinates(scanner,reportType);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void showReportByCity(Scanner scanner,String reportType) {
        System.out.println("Enter the city name: ");
        String city = scanner.next();
        String report;


        try {
            System.out.println("Processing...");
            double[] coordinates = locationManagerInterface.convertToCoordinates(city);
            double latitude = coordinates[0];
            double longitude = coordinates[1];
            report = dataManagerInterface.fetchReport(latitude, longitude, reportType);

            if(reportType.equals("Weather"))
            {
                currentWeatherWindow.showCurrentWeather(report);
            } else if (reportType.equals("Forecast")) {
                forecastWindow.showForecasts(report);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showReportByCoordinates(Scanner scanner,String reportType) {
        String report;

        try {
            System.out.println("Enter the latitude: ");
            double latitude = scanner.nextDouble();
            System.out.println("Enter the longitude: ");
            double longitude = scanner.nextDouble();
            System.out.println("Processing...");

            // Call verifyCoordinates method to ensure the validity of coordinates
            double[] verifiedCoordinates = locationManagerInterface.verifyCoordinates(latitude, longitude);
            latitude = verifiedCoordinates[0];
            longitude = verifiedCoordinates[1];

            report = dataManagerInterface.fetchReport(latitude, longitude, reportType);

            if(reportType.equals("Weather"))
            {
                currentWeatherWindow.showCurrentWeather(report);
            }
            else if (reportType.equals("Forecast")) {
                forecastWindow.showForecasts(report);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
