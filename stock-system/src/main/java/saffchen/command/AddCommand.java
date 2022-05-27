package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;

import java.util.Arrays;

public class AddCommand implements Command {
    private Product product;

    public AddCommand(Product product) {
        this.product = product;
    }

    @Override
    public void doCommand() {
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        fileStorageUtils.addProduct(new Product("Test record",
                "Description for test record",
                1200d,
                Arrays.asList("test1 tag", "test2 tag"),
                "test category",
                6,
                "Ekaterinburg"));
    }

    @Override
    public String getInfo(){
        return "* Write an \"add_product\" if you want to additional product";
    }

}
