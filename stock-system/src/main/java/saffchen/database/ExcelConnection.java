package saffchen.database;

public class ExcelConnection implements Connection{
        private static ExcelConnection connection;
        private String filePath = null;

        private ExcelConnection (String path) {
            this.filePath = path;
        }

        public String getFilePath() {
            return filePath;
        }

        public static ExcelConnection getInstance(String path) {
            if (connection == null) {
                connection = new saffchen.database.ExcelConnection(path);
            }
            return connection;
        }

}
