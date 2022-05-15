package www;

public class AddCommand implements Command{
    private Database database;
    private Product product;
    public AddCommand(Database database, Product product) {
        this.database = database;
        this.product = product;
    }

    @Override
    public void doCommand() {
        database.add_product(product);
    }
}
