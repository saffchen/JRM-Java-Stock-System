package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.database.FileConnection;
import saffchen.entities.ProductEntity;
import saffchen.entities.StoreEntity;
import saffchen.utils.FileStorageUtils;
import saffchen.utils.FileUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class AddCommand implements Command {
    private Exit exit;

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    private static final Logger LOGGER
            = LoggerFactory.getLogger(AddCommand.class);
    static ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure()
                                                         .buildValidatorFactory();
    static Validator validator = validatorFactory.getValidator();

    @Override
    public String getInfo() {
        return "Write an \"add_product\" if you want to additional product";
    }

    public ProductEntity addNewProduct() throws Exception {
        LOGGER.info(" --- ADD_PRODUCT --- ");
        ProductEntity product = null;
        System.out.println("*** ADDING A PRODUCT ***");
        boolean isValidProduct = true;
        while (isValidProduct) {
            System.out.println("Введите продукт или exit для того, чтобы выйти в главное меню");
            System.out.print("Укажите название продукта: ");
            String name = new Scanner(System.in).nextLine().trim();
            if (name.equals("exit")) {
                setExit(new ExitFromCommandMenu());
                exit.doExit();
            }
            System.out.print("Укажите описание продукта: ");
            String description = new Scanner(System.in).nextLine();
            System.out.print("Укажите цену продукта: ");
            Double price;
            try {
                price = new Scanner(System.in).nextDouble();
            } catch (Exception e) {
                System.out.println("Вы ввели некорректное значение цены продукта. Пожалуйста заполните продукт заново.");
                continue;
            }
            System.out.print("Введите теги : ");
            String[] tags = new Scanner(System.in).nextLine().trim().split(",");
            System.out.print("Укажите категорию продукта: ");
            String category = new Scanner(System.in).nextLine().trim();
            System.out.print("Укажите количество продукта: ");
            Integer count;
            try {
                count = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.println("Вы ввели некорректное значение количества продукта. Пожалуйста заполните продукт заново.");
                continue;
            }
            System.out.println("Доступные склады: \n" + FileUtils.getSatelliteString());
            System.out.print("Укажите склад на котором хранится продукт: ");
            String store = new Scanner(System.in).next().toUpperCase(Locale.ROOT);

            product = new ProductEntity(0L, name, description, price, List.of(tags), category,
                    count, new StoreEntity(store));
            LOGGER.info(" --- ADD_PRODUCT --- {{}}", product);
            isValidProduct = false;
            Set<ConstraintViolation<ProductEntity>> violations = validator.validate(product);
            for (ConstraintViolation<ProductEntity> warning : violations) {
                System.out.println(warning.getMessage());
                warning.getMessage();
                isValidProduct = true;
            }
        }
        return product;
    }

    @Override
    public void doCommand() throws Exception {
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        try {
            fileStorageUtils.addProduct(addNewProduct());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }
}
