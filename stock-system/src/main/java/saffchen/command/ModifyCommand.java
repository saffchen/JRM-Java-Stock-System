package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;

import java.util.Arrays;

public class ModifyCommand implements Command {

    @Override
    public String getInfo() {
        return "* Write an \"modify_product\" if you want to make changes";
    }

    @Override
    public void doCommand() {
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        fileStorageUtils.modifyProduct(new Product("Test record Modified",
                        "Description for test record",
                        1200d,
                        Arrays.asList("test1 tag", "test2 tag"),
                        "test category",
                        6,
                        "Ekaterinburg"
                ), new Product("Test record",
                        "Modified Description for test record",
                        1200d,
                        Arrays.asList("test1 tag", "test2 tag"),
                        "test category",
                        6,
                        "Modified Ekaterinburg")
        );
    }
}
