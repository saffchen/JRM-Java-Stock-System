package saffchen.command;

import saffchen.product.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteCommand implements Command {
    private Receiver receiver;

    public DeleteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    private void delete() throws IOException {
        System.out.println("Введите имя продукта, который вы хотите удалить/Please, input the product name for deletion");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String title = bufferedReader.readLine();
        Product product = receiver.getProductByTitle(title);
        if (product == null) {
            System.out.printf("Данный продукт %s не найден/There is no %s product%n", title);
        } else {
            System.out.printf("Вы действительно хотите удалить %s?(Да/Нет)/Do you really want to delete %s?(Yes/No)", title);
            String answer = bufferedReader.readLine();
            if (answer.equals("Да") || answer.equals("Yes")) {
                receiver.deleteProduct(product);
            }
        }
        bufferedReader.close();
    }

    @Override
    public void doCommand() throws IOException {
        delete();
    }
}
