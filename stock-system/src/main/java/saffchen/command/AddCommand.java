package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;

public class AddCommand implements Command {
    private Product product;

    public AddCommand(Product product) {
        this.product = product;
    }

    @Override
    public void doCommand() {
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        fileStorageUtils.addProduct(new Product());
    }

    @Override
    public String getInfo(){
        return "2 Write an \"add_product\" if you want to additional product";
    }

}
