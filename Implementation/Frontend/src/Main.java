import BusinessLogic.CacheManager;
import BusinessLogic.DataManager;
import CacheStorage_DataBase.CacheStorage;

import java.sql.SQLException;

public class Main {

        CacheManager cacheManager = new CacheStorage();
        DataManager.DataManagerInterface dataManagerInterface = new DataManager.Data_Manager(cacheManager);

    public Main() throws SQLException {
    }
}
