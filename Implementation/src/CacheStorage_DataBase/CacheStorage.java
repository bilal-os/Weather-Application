package CacheStorage_DataBase;

import java.sql.*;
import java.util.Vector;

import BusinessLogic.src.CacheManager;
import org.json.JSONObject;
import org.json.JSONArray;

public class CacheStorage extends CacheManager{
    private Connection connection;
    private String weatherTable = "WeatherReports";
    private String airTable = "AirReports";
    private String forecastTable = "ForecastReports";

    public CacheStorage() throws SQLException {
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

    public boolean storeLocation(String locationDetails) throws SQLException {
        String sql = "INSERT INTO locations (cityName, latitude, longitude, country, state) VALUES (?, ?, ?, ?, ?)";
        try {
            JSONArray jsonArray = new JSONArray(locationDetails);
            JSONObject jsonLocation = jsonArray.getJSONObject(0);
            String cityName = jsonLocation.getString("name");
            double latitude = jsonLocation.getDouble("lat");
            double longitude = jsonLocation.getDouble("lon");
            String country = jsonLocation.getString("country");
            String state = jsonLocation.optString("state", null);
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, cityName);
                pstmt.setDouble(2, latitude);
                pstmt.setDouble(3, longitude);
                pstmt.setString(4, country);
                pstmt.setString(5, state);
                int rowsInserted = pstmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {

            throw e;
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
}




