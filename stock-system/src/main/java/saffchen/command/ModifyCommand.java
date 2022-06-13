package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.database.Authorization;
import saffchen.database.FileConnection;
import saffchen.database.User;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static saffchen.utils.MenuUtils.*;
import static saffchen.utils.ValidationUtil.validPositiveDouble;
import static saffchen.utils.ValidationUtil.validPositiveInteger;

public class ModifyCommand implements Command {
    private static User authUser = null;
    private static final Authorization AUTHORIZATION = new Authorization();
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ModifyCommand.class);

    private BufferedReader bufferedReader;
    private Exit exit;

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    @Override
    public String getInfo() {
        return "Write an \"modify_product\" if you want to make changes";
    }

    public void isAuthorizedSuccessfully() {
        if (ModifyCommand.authUser == null) {
            Scanner creds = new Scanner(System.in);
            boolean isFailed = true;
            try {

                for (int i = 0; i < Authorization.ATTEMPT_COUNT; i++) {

                    System.out.println("Enter login and password (Attempt count = " + (Authorization.ATTEMPT_COUNT - i) + ")");
                    System.out.print("login: ");
                    String login = creds.nextLine().trim().toLowerCase();
                    if (login.equals("exit")) {
                        setExit(new ExitFromCommandMenu());
                        exit.doExit();
                    }
                    System.out.print("Enter the password: ");
                    String password = creds.nextLine();

                    ModifyCommand.authUser = AUTHORIZATION.authorize(login, password);
                    if (ModifyCommand.authUser == null)
                        System.out.println("Fail: Check login or password");
                    else {
                        System.out.println("Successful!");
                        isFailed = false;
                        break;
                    }
                }
                if (isFailed)
                    return;
            } catch (Exception e) {
                System.out.println("Error: Authorization was broken!");
            }
        }

    }

    @Override
    public void doCommand() throws IOException {
        LOGGER.info(" --- MODIFY_PRODUCT --- ");

        isAuthorizedSuccessfully();

        while (ModifyCommand.authUser != null) {

            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String inputString = inputProductNameOrExit();
            FileStorageUtils fileStorageUtils = new FileStorageUtils(FileConnection.getInstance("stock_import_csv.csv"));
            Product product = fileStorageUtils.getProductByTitle(inputString);

            if (product != null) {
                System.out.println(product.toString());
                Field[] fields = Product.class.getDeclaredFields();
                Map<String, String> newFieldsMap = new HashMap<>();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    System.out.print(String.format("Please, enter a new %s: ", fieldName.toUpperCase()));
                    inputString = bufferedReader.readLine()
                            .trim();
                    if (inputString.equals("") || isExit(inputString)) {
                        // Converting the List to String with a "\\s" delimiter
                        if (fieldName.equals("tags")) {
                            inputString = ((List<String>) Optional.ofNullable(runGetter(field, product))
                                    .orElse("")).stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(" "));
                        } else {
                            inputString = String.valueOf(runGetter(field, product));
                        }
                        // Price validation
                    } else if (fieldName.equals("price") && !validPositiveDouble(inputString)) {
                        do {
                            inputString = inputCorrectValue(field, product);
                        }
                        while (!isDoubleValidOrExit(inputString));
                        // Count validation
                    } else if (fieldName.equals("count") && !validPositiveInteger(inputString)) {
                        do {
                            inputString = inputCorrectValue(field, product);
                        } while (!isIntegerValidOrExit(inputString));
                    }
                    newFieldsMap.put(fieldName, inputString);
                    System.out.println();
                }
                //https://github.com/logfellow/logstash-logback-encoder#pattern-json-provider
                //https://www.loggly.com/ultimate-guide/java-logging-basics/
                //https://stackoverflow.com/questions/22615311/is-there-a-logback-layout-that-creates-json-objects-with-message-parameters-as-a
                LOGGER.info("--- MODIFY_PRODUCT --- {}", product);
                fileStorageUtils.modifyProduct(product, new Product(newFieldsMap));
            } else {
                System.out.println(String.format("Данный продукт %s не найден/There is no %<s product", inputString));
            }

            inputString = inputProductNameOrExit();
        }
    }

    private String inputProductNameOrExit() throws IOException {
        System.out.println("Введите имя продукта, который вы хотите изменить/Please, input the product name for update");
        System.out.println("Или введите Exit для выхода из этой команды/Either input Exit to exit from modify_product");
        System.out.print("Имя продукта/The product name is: ");
        return bufferedReader.readLine().trim();
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
            inputString = String.valueOf(runGetter(field, product));
        }
        return inputString;
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