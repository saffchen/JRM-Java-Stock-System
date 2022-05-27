package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;

import java.util.Arrays;

public class DeleteCommand implements Command {

    public String getInfo(){
        return "* Write an \"delete_product\" if you want to delete product";
    }

    @Override
    public void doCommand() {
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        fileStorageUtils.deleteProduct(new Product("Test record",
                        "Description for test record",
                        1200d,
                        Arrays.asList("test1 tag", "test2 tag"),
                        "test category",
                        6,
                        "Ekaterinburg"
                )
        );
    }
}
