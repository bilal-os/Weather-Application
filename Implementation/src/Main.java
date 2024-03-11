import BusinessLogic.src.DataManager;
public class Main {
    public static void main(String[] args) {

        DataManager.DataManagerInterface dm = new DataManager.DataManagerInterface();
       System.out.println( dm.fetchWeatherReport(33.44,-94.04));

    }
}