package CacheStorage_DataBase;

import BusinessLogic.src.CacheManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CacheStorage extends CacheManager {

    public boolean storeWeatherReport(double latitude, double longitude, String weatherReport) {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";

        // Database credentials
        String username = "sa";
        String password = "123BIL@l789";

        String sql = "INSERT INTO WeatherReport VALUES (?, ?, ?)";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);
            pstmt.setString(3, weatherReport);

            // Execute the insert query
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Weather report inserted successfully!");
                return true;
            } else {
                System.out.println("Weather report insertion failed!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return false;
        } finally {
            // Close the connection in the finally block
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection Closed!");
                } catch (SQLException e) {
                    System.out.println("Failed to close connection!");
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean storeAirReport(double latitude, double longitude, String airReport)
    {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";

        // Database credentials
        String username = "sa";
        String password = "123BIL@l789";

        String sql = "INSERT INTO airReport VALUES (?, ?, ?)";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);
            pstmt.setString(3, airReport);

            // Execute the insert query
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Air report inserted successfully!");
                return true;
            } else {
                System.out.println("Air report insertion failed!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return false;
        } finally {
            // Close the connection in the finally block
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection Closed!");
                } catch (SQLException e) {
                    System.out.println("Failed to close connection!");
                    e.printStackTrace();
                }
            }
        }
    }
    public boolean storeForecast(double latitude, double longitude, String forecast)
    {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";

        // Database credentials
        String username = "sa";
        String password = "123BIL@l789";

        String sql = "INSERT INTO forecast VALUES (?, ?, ?)";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);
            pstmt.setString(3, forecast);

            // Execute the insert query
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Air report inserted successfully!");
                return true;
            } else {
                System.out.println("Air report insertion failed!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return false;
        } finally {
            // Close the connection in the finally block
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection Closed!");
                } catch (SQLException e) {
                    System.out.println("Failed to close connection!");
                    e.printStackTrace();
                }
            }
        }
    }

}
