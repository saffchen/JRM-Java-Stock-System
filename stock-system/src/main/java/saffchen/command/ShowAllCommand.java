package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.database.FileConnection;
import saffchen.utils.FileStorageUtils;

public class ShowAllCommand implements Command {
    private static final Logger logger
            = LoggerFactory.getLogger(ShowAllCommand.class);
    @Override
    public String getInfo() {
        return "Write an \"show_all\" if you want to view all positions";
    }

    @Override
    public void doCommand() {
        logger.info(" --- SHOW_ALL ---");
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        fileStorageUtils.showAllProducts();
    }
}
