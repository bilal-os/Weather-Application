module Core {
    requires json.java;
    requires java.sql;
    exports BusinessLogic;
    exports CacheStorage_DataBase;
    exports CacheStorage_TextFiles;
}