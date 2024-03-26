    package BusinessLogic;

    import org.json.JSONObject;

    import java.util.regex.Matcher;
    import java.util.regex.Pattern;

    public class NotificationManager {
        private Boolean enableStatus;
        private String currentLocation;
        private CacheManager cacheManager;

        private  double[] coordinates;

        public NotificationManager(CacheManager cacheManager)
        {
            this.cacheManager=cacheManager;
            currentLocation = null;
            enableStatus = false;
            coordinates = null;
        }

        public String enableNotification() throws Exception {
            try {
                currentLocation = cacheManager.fetchCurrentLocation();
                if (currentLocation != null) {
                    coordinates = extractLongitudeLatitude(currentLocation);
                    enableStatus = true;
                    return "Notification Enabled";
                } else {
                    throw new Exception("Set a current location");
                }
            } catch (Exception e) {
                throw e;
            }
        }

        private String extractValue(String input, String key) {
            String pattern = key + ":\\s*([^,]+)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
            return null;
        }

        public String raiseNotification() throws Exception {
            try {
                StringBuilder notification = new StringBuilder("Weather Alert:\n");

                String city = extractValue(currentLocation, "City");
                String state = extractValue(currentLocation, "State");
                String country = extractValue(currentLocation, "Country");

                // Append location details
                notification.append("Location Details:\n");
                notification.append("City: ").append(city).append("\n");
                if (state != null) {
                    notification.append("State: ").append(state).append("\n");
                }
                if (country != null) {
                    notification.append("Country: ").append(country).append("\n");
                }

                if (enableStatus) {
                    String weatherAssessmentResult = weatherAssessment(cacheManager.fetchReport(coordinates[0], coordinates[1], "Weather"));
                    String airAssessmentResult = airAssessment(cacheManager.fetchReport(coordinates[0], coordinates[1], "Air"));

                    // Append weather assessments
                    notification.append("\nWeather Assessment:\n");
                    if (weatherAssessmentResult != null) {
                        notification.append(weatherAssessmentResult).append("\n");
                    } else {
                        notification.append("No significant weather conditions.\n");
                    }
                    // Append air assessments
                    notification.append("\nAir Quality Assessment:\n");
                    if (airAssessmentResult != null) {
                        notification.append(airAssessmentResult).append("\n");
                    } else {
                        notification.append("No significant air quality issues.\n");
                    }
                    return notification.toString();
                } else {
                    throw new Exception("Enable notification");
                }
            } catch (Exception e) {
                throw e;
            }
        }


        private String weatherAssessment(String weatherreport) throws Exception{
            StringBuilder assessment = new StringBuilder();

            try {
                // Parse the JSON string into a JSONObject
                JSONObject jsonObject = new JSONObject(weatherreport);

                // Check for significant weather conditions
                JSONObject main = jsonObject.getJSONObject("main");
                double temperature = main.getDouble("temp");

                // Assess temperature
                if (temperature > 30.0) {
                    assessment.append("Temperature is too high: ").append(temperature).append("°C. ");
                }

                // Check for other weather conditions
                if (jsonObject.has("weather")) {
                    JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
                    String mainWeather = weather.getString("main");
                    if (mainWeather.equalsIgnoreCase("Rain")) {
                        assessment.append("It is raining. ");
                    } else if (mainWeather.equalsIgnoreCase("Snow")) {
                        assessment.append("It is snowing. ");
                    } // Add more conditions for other weather types as needed
                }

                // Check for extreme wind speed
                if (jsonObject.has("wind")) {
                    JSONObject wind = jsonObject.getJSONObject("wind");
                    double windSpeed = wind.getDouble("speed");
                    if (windSpeed > 20.0) { // Example threshold for extreme wind speed
                        assessment.append("Extreme wind speed: ").append(windSpeed).append(" m/s. ");
                    }
                }

            } catch (Exception e) {
                throw e;
            }

            // If assessment is empty, return null
            return assessment.length() > 0 ? assessment.toString() : null;
        }

        private String airAssessment(String airreport) {
            StringBuilder assessment = new StringBuilder();

            try {
                // Parse the JSON string into a JSONObject
                JSONObject jsonObject = new JSONObject(airreport);

                // Check if the 'main' object exists
                if (jsonObject.has("main")) {
                    JSONObject main = jsonObject.getJSONObject("main");

                    // Check if the 'aqi' value exists
                    if (main.has("aqi")) {
                        int aqi = main.getInt("aqi");

                        // Assess air quality based on AQI value
                        if (aqi <= 50) {
                            assessment.append("Air quality is good (AQI: ").append(aqi).append("). ");
                        } else if (aqi <= 100) {
                            assessment.append("Air quality is moderate (AQI: ").append(aqi).append("). ");
                        } else if (aqi <= 150) {
                            assessment.append("Air quality is unhealthy for sensitive groups (AQI: ").append(aqi).append("). ");
                        } else if (aqi <= 200) {
                            assessment.append("Air quality is unhealthy (AQI: ").append(aqi).append("). ");
                        } else if (aqi <= 300) {
                            assessment.append("Air quality is very unhealthy (AQI: ").append(aqi).append("). ");
                        } else {
                            assessment.append("Air quality is hazardous (AQI: ").append(aqi).append("). ");
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace(); // Handle parsing errors appropriately
            }

            // If assessment is empty, return null
            return assessment.length() > 0 ? assessment.toString() : null;
        }

        private double[] extractLongitudeLatitude(String currentLocation) {
            // Initialize latitude and longitude variables
            double latitude = Double.NaN;
            double longitude = Double.NaN;

            // Split the currentLocation string by whitespace
            String[] words = currentLocation.split("\\s+");

            // Iterate through the words to find latitude and longitude
            for (int i = 0; i < words.length; i++) {
                // Convert the word to lowercase for case-insensitive matching
                String word = words[i].toLowerCase();

                // Check if the word contains "latitude" or "longitude"
                if (word.contains("latitude") && i + 1 < words.length) {
                    // Parse the latitude value from the next word
                    latitude = Double.parseDouble(words[i + 1].replaceAll("[^\\d.-]", ""));
                } else if (word.contains("longitude") && i + 1 < words.length) {
                    // Parse the longitude value from the next word
                    longitude = Double.parseDouble(words[i + 1].replaceAll("[^\\d.-]", ""));
                }
            }

            // Create and return the array containing latitude and longitude
            return new double[]{latitude, longitude};
        }

        }






