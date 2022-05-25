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

        System.out.println("*** ADDITIONAL PRODUCT ***");
        System.out.print("Укажите название продукта: ");
        String title = new Scanner(System.in).next();
        System.out.print("Укажите описание продукта: ");
        String description = new Scanner(System.in).next();
        System.out.print("Укажите цену продукта: ");
        int price = new Scanner(System.in).nextInt();
        System.out.print("Введите теги: ");
        String tags = new Scanner(System.in).next().trim();
        System.out.print("Укажите категорию продукта: ");
        String category = new Scanner(System.in).next().trim();
        System.out.print("Укажите количество продукта: ");
        int count = new Scanner(System.in).nextInt();
        System.out.print("Укажите склад на котором хранится продукт: ");
        String satellite = new Scanner(System.in).next().toUpperCase(Locale.ROOT);

        Product additionalProduct = new Product(title, description, price, Collections.singletonList(tags), category,
                count, satellite);

        Set<ConstraintViolation<Product>> violations = validator.validate(additionalProduct);
        for (ConstraintViolation<Product> warning : violations) {
            System.out.println(warning.getMessage());
        }
    }
}