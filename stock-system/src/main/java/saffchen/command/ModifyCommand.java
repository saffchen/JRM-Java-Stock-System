package saffchen.command;

import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static saffchen.utils.MenuUtils.*;
import static saffchen.utils.ValidationUtil.isStringToDoubleValid;
import static saffchen.utils.ValidationUtil.isStringToIntegerValid;

public class ModifyCommand implements Command {

    private BufferedReader bufferedReader;

    @Override
    public String getInfo() {
        return "Write an \"modify_product\" if you want to make changes";
    }

    @Override
    public void doCommand() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = "";
        do {
            System.out.println("Введите имя продукта, который вы хотите изменить/Please, input the product name for update");
            System.out.println("Или введите Exit для выхода из этой команды/Either input Exit to exit from modify_product");
            System.out.print("Имя продукта/The product name is: ");
            inputString = bufferedReader.readLine().trim();
            FileStorageUtils fileStorageUtils = new FileStorageUtils(FileConnection.getInstance("stock_import_csv.csv"));
            Product product = fileStorageUtils.getProductByTitle(inputString);
            Field[] fields = Product.class.getDeclaredFields();

            if (product == null && !isExit(inputString)) {
                System.out.println(String.format("Данный продукт %s не найден/There is no %<s product", inputString));
            } else {
                System.out.println(product.toString());
                Map<String, String> newFieldsMap = new HashMap<>();
                for (int x = 0; x < fields.length; x++) {
                    Field field = fields[x];
                    String fieldName = field.getName();
                    System.out.print(String.format("Please, enter a new %s: ", fieldName.toUpperCase()));
                    inputString = bufferedReader.readLine()
                                                .trim();
                    if (inputString.equals("") || isExit(inputString)) {
                        // Converting the List to String with a "\\s" delimiter
                        if (fieldName.equals("tags")) {
                            inputString = ((List<String>) runGetter(field, product)).stream()
                                                                                    .map(String::valueOf)
                                                                                    .collect(Collectors.joining(" "));
                        } else {
                            inputString = getInputString(field, product);
                        }
                        // Price validation
                    } else if (fieldName.equals("price") && !isStringToDoubleValid(inputString)) {
                        do {
                            inputString = inputCorrectValue(field, product);
                        }
                        while (!isDoubleValidOrExit(inputString));
                        // Count validation
                    } else if (fieldName.equals("count") && !isStringToIntegerValid(inputString)) {
                        do {
                            inputString = inputCorrectValue(field, product);
                        } while (!isIntegerValidOrExit(inputString));
                    }
                    newFieldsMap.put(fieldName, inputString);
                    System.out.println();
                }
                fileStorageUtils.modifyProduct(product, new Product(newFieldsMap));
            }
        } while (!isExit(inputString));
    }

    private String inputCorrectValue(Field field, Product product) throws IOException {
        String fieldName = field.getName()
                                .toUpperCase();
        String inputString;
        System.out.println(String.format("Пожалуйста, введите корректную %s. %<s может состоять только из цифр./" +
                "Please input a correct %<s. %<s can consist the digits only", fieldName));
        System.out.print(String.format("Please, enter a new %s: ", fieldName));
        inputString = bufferedReader.readLine()
                                    .trim();
        if (inputString.equals("") || isExit(inputString)) {
            inputString = getInputString(field, product);
        }
        return inputString;
    }

    private String getInputString(Field field, Product product) {
        return String.valueOf(runGetter(field, product));
    }

    // https://stackoverflow.com/questions/13400075/reflection-generic-get-field-value
    private Object runGetter(Field field, Product product) {
        for (Method method : product.getClass()
                                    .getMethods()) {
            if ((method.getName()
                       .startsWith("get")) && (method.getName()
                                                     .length() == (field.getName()
                                                                        .length() + 3))) {
                if (method.getName()
                          .toLowerCase()
                          .endsWith(field.getName()
                                         .toLowerCase())) {
                    try {
                        return method.invoke(product);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("Could not determine method: " + method.getName());
                    }
                }
            }
        }
        return null;
    }
}
