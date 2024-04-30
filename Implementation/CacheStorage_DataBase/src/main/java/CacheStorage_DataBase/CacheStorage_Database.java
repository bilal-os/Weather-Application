package CacheStorage_DataBase;

import BusinessLogic.CacheManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.Vector;


public class CacheStorage_Database extends CacheManager{
    private Connection connection;
    private String weatherTable = "WeatherReports";
    private String airTable = "AirReports";
    private String forecastTable = "ForecastReports";

    public CacheStorage_Database() throws SQLException {
            connection = getConnection();
    }

    @Override
    protected void finalize() {
        closeConnection();
    }

    private Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseCache;trustServerCertificate=true";
                String username = "sa";
                String password = "123BIL@l789";    
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {

            throw e;
        }
        return connection;
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean storeReport(double latitude, double longitude, String reportType, String report) throws SQLException {

        String tableName;
        tableName = getTableName(reportType);

        String sql = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);
            pstmt.setString(3, report);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw e;
        }
    }

    public String fetchReport(double latitude, double longitude, String reportType) throws SQLException {

        String tableName;

       tableName = getTableName(reportType);

        String sql = "SELECT report FROM " + tableName + " WHERE lat = ? AND lon = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString("report") : null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    private String getTableName(String reportType) throws SQLException {
        switch (reportType) {
            case "Weather":
                return weatherTable;
            case "Air":
                return airTable;
            case "Forecast":
                return forecastTable;
            default:
                throw new SQLException("Invalid report type: " + reportType);
        }
    }

    public boolean storeLocation(String locationDetails, Boolean current, Boolean locationExists) throws SQLException {
        if (locationExists) {
            String updateSql = "UPDATE locations SET isCurrent = 1 WHERE latitude = ? AND longitude = ?";
            try {
                JSONArray jsonArray = new JSONArray(locationDetails);
                JSONObject jsonLocation = jsonArray.getJSONObject(0);
                double latitude = jsonLocation.getDouble("lat");
                double longitude = jsonLocation.getDouble("lon");

                try (PreparedStatement pstmt = connection.prepareStatement(updateSql)) {
                    pstmt.setDouble(1, latitude);
                    pstmt.setDouble(2, longitude);
                    int rowsUpdated = pstmt.executeUpdate();
                    return rowsUpdated > 0;
                }
            } catch (SQLException e) {
                throw e;
            }
        } else {
            String insertSql = "INSERT INTO locations (cityName, latitude, longitude, country, state, isCurrent) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                JSONArray jsonArray = new JSONArray(locationDetails);
                JSONObject jsonLocation = jsonArray.getJSONObject(0);
                String cityName = jsonLocation.getString("name");
                double latitude = jsonLocation.getDouble("lat");
                double longitude = jsonLocation.getDouble("lon");
                String country = jsonLocation.getString("country");
                String state = jsonLocation.optString("state", null);
                int isCurrent = (current != null && current) ? 1 : 0;

                try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
                    pstmt.setString(1, cityName);
                    pstmt.setDouble(2, latitude);
                    pstmt.setDouble(3, longitude);
                    pstmt.setString(4, country);
                    pstmt.setString(5, state);
                    pstmt.setInt(6, isCurrent);
                    int rowsInserted = pstmt.executeUpdate();
                    return rowsInserted > 0;
                }
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public Vector<String> fetchStoredLocations() throws SQLException {
        Vector<String> locations = new Vector<>();
        String sql = "SELECT cityName, latitude, longitude, country, state FROM locations";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String cityName = rs.getString("cityName");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String country = rs.getString("country");
                String state = rs.getString("state");

                StringBuilder locationString = new StringBuilder("City: " + cityName);
                if (state != null && !state.isEmpty()) {
                    locationString.append(", State: ").append(state);
                }
                locationString.append(", Country: ").append(country)
                        .append(", Latitude: ").append(latitude)
                        .append(", Longitude: ").append(longitude);

                locations.add(locationString.toString());
            }
        } catch (SQLException e) {
            throw e;
        }
        return locations;
    }

    public String fetchCurrentLocation() throws Exception {
        String sql = "SELECT cityName, latitude, longitude, country, state FROM locations where isCurrent = 1";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) { // Check if there is at least one row in the result set
                String cityName = rs.getString("cityName");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                String country = rs.getString("country");
                String state = rs.getString("state");

                StringBuilder locationString = new StringBuilder("City: " + cityName);
                if (state != null && !state.isEmpty()) {
                    locationString.append(", State: ").append(state);
                }
                locationString.append(", Country: ").append(country)
                        .append(", Latitude: ").append(latitude)
                        .append(", Longitude: ").append(longitude);
                return locationString.toString();
            } else {
                return null; // Return null if no current location found
            }
        } catch (SQLException e) {
            throw e;
        }
    }


}




