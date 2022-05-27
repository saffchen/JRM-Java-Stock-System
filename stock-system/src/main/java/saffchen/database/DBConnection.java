package saffchen.database;

public class DBConnection implements Connection {
    private static DBConnection instance;

    private DBConnection() {
    }

    public static DBConnection getInstance(String path) {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
}
