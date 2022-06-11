package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;
import saffchen.utils.MenuUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteCommand implements Command {
    private static final Logger logger
            = LoggerFactory.getLogger(DeleteCommand.class);
    @Override
    public String getInfo() {
        return "Write an \"delete_product\" if you want to delete product";
    }

    @Override
    public void doCommand() throws IOException {
        logger.info(" --- DELETE_PRODUCT --- ");
        System.out.println("Введите имя продукта, который вы хотите удалить/Please, input the product name for deletion");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Имя продукта/The product name is: ");
        String title = bufferedReader.readLine();

        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        Product product = fileStorageUtils.getProductByTitle(title);
        logger.info(" --- DELETE_PRODUCT --- {{}}", product);
        if (product == null) {
            System.out.println(String.format("Данный продукт %s не найден/There is no %<s product", title));
        } else {
            System.out.println(String.format("Вы действительно хотите удалить %s?(Да/Нет)/Do you really want to delete %<s?(Yes/No)", title));
            String answer = bufferedReader.readLine();
            if (MenuUtils.isYes(answer)) {
                fileStorageUtils = new FileStorageUtils(fileConnection);
                System.out.println(String.format("Deleting the product %s", title));
                fileStorageUtils.deleteProduct(product);
            } else {
                System.out.println(String.format("Вы не подтвердили удаление вводом команды Да. Вы вели %s." +
                        "/You haven't confirmed the deletion using the command Yes. You input is %<s", answer));
            }
        }
    }
}