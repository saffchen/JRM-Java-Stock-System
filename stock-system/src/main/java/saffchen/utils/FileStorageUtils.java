package saffchen.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import saffchen.database.FileConnection;
import saffchen.product.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStorageUtils implements StorageUtils {
    private FileConnection fileConnection;

    public FileStorageUtils(FileConnection fileConnection) {
        this.fileConnection = fileConnection;
    }

    @Override
    public void addProduct(Product product) {
        try {
            //NOP
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public void modifyProduct() {

    }

    @Override
    public void showAllProducts() {

    }

    public List<Product> getDataForReportBySatelliteFromCSV(String criteries) {
        List<Product> products = new ArrayList<>();
        CSVParser csvParser = null;
        CSVReader csvReader = null;

        try {
            csvParser = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(new FileReader(fileConnection.getFilePath()))
                    .withSkipLines(1)
                    .withCSVParser(csvParser).build();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord[nextRecord.length - 1]
                        .trim().toUpperCase().contains(criteries)) {

                    Product product = new Product(nextRecord[0], //Title
                                                nextRecord[1], //Description
                                                Double.valueOf(nextRecord[2]), //Price
                                                Arrays.asList(nextRecord[3].split(",")) , //Tags
                                                nextRecord[4], //Category
                                                Integer.valueOf(nextRecord[5]), // Count
                                                nextRecord[6] //Satellite
                                                );

                    products.add(product);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't find the database file");
        } catch (IOException e) {
            System.out.println("Error: Can't read the database file");
        } catch (Exception e) {
            System.out.println("Error: Unknown error. Try to get correct information from database!");
        }
        return products;
    }

}
