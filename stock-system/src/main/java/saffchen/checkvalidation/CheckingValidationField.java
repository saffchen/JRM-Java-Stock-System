package saffchen.checkvalidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

import java.util.*;

public class CheckingValidationField {

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Product product;
        System.out.println("*** ADDITIONAL PRODUCT ***");

        boolean isValidProduct = true;
        while (isValidProduct) {
            System.out.println("Введите продукт или exit для того, чтобы выйти в главное меню");
            System.out.print("Укажите название продукта: ");
            String title = new Scanner(System.in).next();
            if (title.equals("exit"))
                break;
            System.out.print("Укажите описание продукта: ");
            String description = new Scanner(System.in).next();
            System.out.print("Укажите цену продукта: ");
            int price;
            try {
                price = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.err.println("Вы ввели некорректное значение. Пожалуйста заполните продукт заново.");
                continue;
            }
            System.out.print("Введите теги (Теги заполняются через запятую, без пробелов): ");
            String[] tags = new Scanner(System.in).next().trim().split(",");
            System.out.print("Укажите категорию продукта: ");
            String category = new Scanner(System.in).next().trim();
            System.out.print("Укажите количество продукта: ");
            int count;
            try {
                count = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.err.println("Вы ввели некорректное значение. Пожалуйста заполните продукт заново. ");
                continue;
            }
            System.out.print("Укажите склад на котором хранится продукт: ");
            String satellite = new Scanner(System.in).next().toUpperCase(Locale.ROOT);

            product = new Product(title, description, price, List.of(tags), category,
                    count, satellite);

            isValidProduct = false;
            Set<ConstraintViolation<Product>> violations = validator.validate(product);
            for (ConstraintViolation<Product> warning : violations) {
                System.err.println(warning.getMessage());
                if (warning.getMessage().contains("!")) {
                    isValidProduct = true;
                }
            }
        }
    }
}