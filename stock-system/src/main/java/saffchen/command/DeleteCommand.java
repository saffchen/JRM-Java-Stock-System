package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteCommand implements Command {

    @Override
    public String getInfo() {
        return "* Write an \"delete_product\" if you want to delete product";
    }

    @Override
    public void doCommand() throws IOException {
        System.out.println("Введите имя продукта, который вы хотите удалить/Please, input the product name for deletion");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String title = bufferedReader.readLine();
        FileStorageUtils fileStorageUtils = null;
        assert false;
        Product product = fileStorageUtils.getProductByTitle(title);
        if (product == null) {
            System.out.printf("Данный продукт %s не найден/There is no %s product%n", title);
        } else {
            System.out.printf("Вы действительно хотите удалить %s?(Да/Нет)/Do you really want to delete %s?(Yes/No)", title);
            String answer = bufferedReader.readLine();
            if (answer.equals("Да") || answer.equals("Yes")) {
                FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
                fileStorageUtils = new FileStorageUtils(fileConnection);
                System.out.printf("Deleting the product %s%n", product.getTitle());
                fileStorageUtils.deleteProduct(product);
            }
        }
        bufferedReader.close();
    }
}
