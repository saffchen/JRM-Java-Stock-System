package saffchen.database;

public class FileConnection implements IConnection {
    private static FileConnection connection;
    private String filePath = null;
    private FileConnection(String path) {
        this.filePath = path;
    }

    public String getFilePath() {
        return filePath;
    }

    public static FileConnection getInstance(String path) {
        if (connection == null) {
            connection = new FileConnection(path);
        }
        return connection;
    }
}
