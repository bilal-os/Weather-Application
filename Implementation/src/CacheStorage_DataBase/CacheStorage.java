package CacheStorage_DataBase;

import BusinessLogic.src.CacheManager;

import java.sql.*;
import java.util.Vector;

import org.json.JSONObject;
import org.json.JSONArray;

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

    public String fetchWeatherReport(double latitude, double longitude) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";

        // Database credentials
        String username = "sa";
        String password = "123BIL@l789";

        String sql = "SELECT report FROM WeatherReport WHERE lat = ? AND lon = ?";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("report");
            } else {
                return "No weather report found";
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return null; // or handle the exception appropriately
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

    public String fetchAirReport(double latitude, double longitude) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";

        // Database credentials
        String username = "sa";
        String password = "123BIL@l789";

        String sql = "SELECT report FROM airReport WHERE lat = ? AND lon = ?";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("report");
            } else {
                return "No air report found";
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return null; // or handle the exception appropriately
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

    public String fetchForecast(double latitude, double longitude) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";

        // Database credentials
        String username = "sa";
        String password = "123BIL@l789";

        String sql = "SELECT report FROM forecast WHERE lat = ? AND lon = ?";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("report");
            } else {
                return "No forecast report found";
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return null; // or handle the exception appropriately
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

    public boolean storeLocation(String locationDetails) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";
        String username = "sa";
        String password = "123BIL@l789";
        String sql = "INSERT INTO locations (cityName, latitude, longitude, country, state) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            JSONArray jsonArray = new JSONArray(locationDetails);
            JSONObject jsonLocation = jsonArray.getJSONObject(0);

            String cityName = jsonLocation.getString("name"); // Assuming "name" in JSON corresponds to "cityName" in SQL
            double latitude = jsonLocation.getDouble("lat");
            double longitude = jsonLocation.getDouble("lon");
            String country = jsonLocation.getString("country");
            String state = jsonLocation.has("state") ? jsonLocation.getString("state") : null;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cityName);
            pstmt.setDouble(2, latitude);
            pstmt.setDouble(3, longitude);
            pstmt.setString(4, country);
            pstmt.setString(5, state);

            // Execute the insert query
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Location inserted successfully!");
                return true;
            } else {
                System.out.println("Location insertion failed!");
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

    public Vector<String> fetchStoredLocations() {
        Vector<String> locations = new Vector<>();

        String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";
        String username = "sa";
        String password = "123BIL@l789";
        String sql = "SELECT cityName, latitude, longitude, country, state FROM locations";

        Connection conn = null; // Declare outside the try block

        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String cityName = rs.getString("cityName");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String country = rs.getString("country");
                String state = rs.getString("state");

                String locationString = cityName + ", " + country;
                if (state != null && !state.isEmpty()) { // Check if state is not null
                    locationString += ", " + state;
                }
                locationString += " (Latitude: " + latitude + ", Longitude: " + longitude + ")";
                locations.add(locationString);
            }
            return locations;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
