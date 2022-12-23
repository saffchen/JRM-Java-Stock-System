package saffchen.database;

public class FileConnection {
    private static FileConnection connection;
    private final String filePath;

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
