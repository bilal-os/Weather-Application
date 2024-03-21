package BusinessLogic.src;

import java.util.Vector;

public class DataManager {

    abstract public static class DataManagerInterface {

       abstract public String fetchReport(double latitude, double longitude,String reportType) throws Exception;

    }

    public static class Data_Manager extends DataManagerInterface{
        private APIService weatherService;
        private CacheManager cacheManager;

        public Data_Manager(CacheManager cacheManager)
        {
            weatherService = new APIService();
            this.cacheManager=cacheManager;
        }

        public String fetchReport(double latitude, double longitude,String reportType) throws Exception
        {
            try{
                String cacheData = cacheManager.fetchReport(latitude,longitude,reportType);

                if(cacheData==null)
                {
                    return weatherService.fetchReport(latitude,longitude,reportType);
                }

                return cacheData;

            }
            catch (Exception e)
            {
                throw e;
            }
        }

        public Vector<String> fetchReports(Vector<String> coordinates,String reportType) throws Exception
        {
            try {
                Vector<String> reports = new Vector<>();

                for (String coordinate : coordinates) {
                    String[] parts = coordinate.split(",");
                    if (parts.length == 2) {
                        double latitude = Double.parseDouble(parts[0].trim());
                        double longitude = Double.parseDouble(parts[1].trim());
                        String airReport = weatherService.fetchReport(latitude, longitude,reportType);
                        if (airReport != null) {
                            reports.add(airReport);
                        }
                    }
                }

                return reports;
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        public boolean storeReport(double latitude, double longitude,String reportType,String report) throws Exception
        {
            try {
                return cacheManager.storeReport(latitude,longitude,reportType,report);
            }
            catch (Exception e)
            {
                throw e;
            }
        }



    }

}
