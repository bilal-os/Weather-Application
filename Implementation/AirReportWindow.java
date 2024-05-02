package Terminal;

import org.json.JSONArray;
import org.json.JSONObject;

public class AirReportWindow {
    private double airIndex;
    private double co;
    private double no;
    private double no2;
    private double o3;
    private double so2;
    private double pm2_5;
    private double pm10;
    private double nh3;

    public AirReportWindow() {
        this.airIndex = 0.0;
        this.co = 0.0;
        this.no = 0.0;
        this.no2 = 0.0;
        this.o3 = 0.0;
        this.so2 = 0.0;
        this.pm2_5 = 0.0;
        this.pm10 = 0.0;
        this.nh3 = 0.0;
    }

    private void formatData(String airReport) throws Exception {
        try {
            JSONObject report = new JSONObject(airReport);

            if (report != null) {
                JSONArray listArray = report.getJSONArray("list");
                JSONObject firstItem = listArray.getJSONObject(0);
                if (firstItem != null) {
                    JSONObject mainObj = firstItem.getJSONObject("main");
                    if (mainObj != null) {
                        airIndex = mainObj.optDouble("aqi");
                    }

                    JSONObject componentsObj = firstItem.getJSONObject("components");
                    if (componentsObj != null) {
                        co = componentsObj.optDouble("co");
                        no = componentsObj.optDouble("no");
                        no2 = componentsObj.optDouble("no2");
                        o3 = componentsObj.optDouble("o3");
                        so2 = componentsObj.optDouble("so2");
                        pm2_5 = componentsObj.optDouble("pm2_5");
                        pm10 = componentsObj.optDouble("pm10");
                        nh3 = componentsObj.optDouble("nh3");
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void showAirReport(String airReport) {
        try {
            formatData(airReport);

            // Displaying the air report information
            System.out.println("Air Report Information:");
            System.out.println("Air Index: " + airIndex);
            System.out.println("CO: " + co);
            System.out.println("NO: " + no);
            System.out.println("NO2: " + no2);
            System.out.println("O3: " + o3);
            System.out.println("SO2: " + so2);
            System.out.println("PM2.5: " + pm2_5);
            System.out.println("PM10: " + pm10);
            System.out.println("NH3: " + nh3);
        } catch (Exception e) {
            System.err.println("Error occurred while displaying air report: " + e.getMessage());
        }
    }


}
