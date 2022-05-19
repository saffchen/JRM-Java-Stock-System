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
import java.util.List;

public class FileStorageUtils implements IStorageUtils {
    private FileConnection fileConnection;

    public FileStorageUtils(FileConnection fileConnection) {
        this.fileConnection = fileConnection;
    }

    @Override
    public void addProduct(Product product) {
        try {
            //NOP
        } catch (Exception e) {
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

    public List<String[]> getDataForReportFromCSV(String criteries) {
        List<String[]> results = new ArrayList<>();
        CSVParser csvParser = null;
        CSVReader csvReader = null;

        try {
            csvParser = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(new FileReader(fileConnection.getFilePath()))
                    .withCSVParser(csvParser).build();

            String[] nextRecord;
            results.add(csvReader.readNext());
            while ((nextRecord = csvReader.readNext()) != null) {
                //System.out.println(nextRecord[nextRecord.length - 1].trim().toUpperCase() +" : " + criteries);
                if (nextRecord[nextRecord.length - 1]
                        .trim().toUpperCase().contains(criteries)) {

                    results.add(nextRecord);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}
