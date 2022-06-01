package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;
import saffchen.utils.MenuUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ModifyCommand implements Command {

    @Override
    public String getInfo() {
        return "Write an \"modify_product\" if you want to make changes";
    }

    @Override
    public void doCommand() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = "";


        do {
            System.out.println("Введите имя продукта, который вы хотите изменить/Please, input the product name for update");
            System.out.println("Или введите Exit для выхода из этой команды/Either input Exit to exit from modify_product");
            System.out.print("Имя продукта/The product name is: ");
            inputString = bufferedReader.readLine();

            FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
            FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
            Product product = fileStorageUtils.getProductByTitle(inputString);
            Field[] fields = Product.class.getDeclaredFields();

            if (product == null && !MenuUtils.isExit(inputString)) {
                System.out.println(String.format("Данный продукт %s не найден/There is no %<s product", inputString));
            } else {
                System.out.println(product.toString());
                Map<String, String> newFieldsMap = new HashMap<>();
                for (int x = 0; x < fields.length; x++) {
                    Field field = fields[x];
                    String fieldName = field.getName();
                    System.out.print(String.format("Please, enter a new %s: ", fieldName.toUpperCase()));
                    inputString = bufferedReader.readLine();
                    if (inputString.equals("")) {
                        inputString = getInputString(field, product);
                    }
                    newFieldsMap.put(fieldName, inputString);
                    System.out.println();
                }
                fileStorageUtils.modifyProduct(product, new Product(newFieldsMap));
            }
        } while (!MenuUtils.isExit(inputString));
    }

    // https://stackoverflow.com/questions/13400075/reflection-generic-get-field-value
    private String getInputString(Field field, Product product) {
        for (Method method : product.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    try {
                        return String.valueOf(method.invoke(product));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("Could not determine method: " + method.getName());
                    }
                }
            }
        }
        return null;
    }
}
