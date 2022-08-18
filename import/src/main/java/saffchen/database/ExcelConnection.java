package saffchen.database;

public class ExcelConnection {
    private static ExcelConnection connection;
    private final String filePath;

    private ExcelConnection(String path) {
        this.filePath = path;
    }

    public String getFilePath() {
        return filePath;
    }

    public static ExcelConnection getInstance(String path) {
        if (connection == null) {
            connection = new ExcelConnection(path);
        }
        return connection;
    }
}
