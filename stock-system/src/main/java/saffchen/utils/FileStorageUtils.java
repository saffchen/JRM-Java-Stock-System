package saffchen.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.product.ProductAdapter;
import saffchen.product.RawProduct;
import saffchen.product.ReflectProductUtils;

import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileStorageUtils implements StorageUtils {
    private FileConnection fileConnection;

    public FileStorageUtils(FileConnection fileConnection) {
        this.fileConnection = fileConnection;
    }

    public List<String> getHeadersFromCSV() {
        List<String> headers = null;
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileConnection.getFilePath()))
                    .withCSVParser(parser)
                    .build();

            headers = Arrays.asList(csvReader.readNext());

            csvReader.close();
        } catch (Exception e) {

            e.printStackTrace();
            System.err.println("Error: Can't get the headers. Try again!");
        }
        return headers;
    }

    public List<RawProduct> getDataFromCSV() {
        List<RawProduct> products = null;
        try {
            CsvToBean<RawProduct> csvToBean = getCSVParser();
            products = csvToBean.parse();

        } catch (Exception e) {

            e.printStackTrace();
            System.err.println("Error: Can't get the data from csv.");
        }
        return products;
    }

    public void addHeadersToCSV(String headers) {
        if (headers == null || headers.isEmpty()){
            System.out.println("Error: There are no headers in the string!");
            return;
        }
        FileWriter productToCsv = null;
        try {
            productToCsv = new FileWriter(fileConnection.getFilePath(), false);
            productToCsv.write(headers);
            productToCsv.close();

            System.out.println("Headers were added to database successfully!");
        } catch (IOException e) {
            System.err.println("Error: Can't write the headers to csv");
            try {
                productToCsv.close();
            } catch (IOException ioException) {
                System.out.println("");
            }
        } catch (Exception e) {
            System.err.println("Error: Can't write the headers to csv");
            try {
                productToCsv.close();
            } catch (IOException ioException) {
                System.out.println("Error: Can't close the csv");
            }
        }
    }



    @Override
    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Error: There is no product for add!");
            return;
        }
        RawProduct rawProduct = new ProductAdapter(product).setDataToRawProduct();
        FileWriter productToCsv = null;
        try {
            productToCsv = new FileWriter(fileConnection.getFilePath(), true);
            productToCsv.write(rawProduct.toCSVString(";"));
            productToCsv.close();

            System.out.println(rawProduct.showInfo());
            System.out.println("Product was added to database successfully!");
        } catch (IOException e) {
            System.err.println("Error: Can't write data");
            try {
                productToCsv.close();
            } catch (IOException ioException) {
                System.out.println("");
            }
        } catch (Exception e) {
            System.err.println("Error: Can't write data");
            try {
                productToCsv.close();
            } catch (IOException ioException) {
                System.out.println("");
            }
        }
    }

    public String addRawProductsFromListToCSV(List<RawProduct> rawProducts) {
        FileWriter productToCsv = null;
        try {
            if (rawProducts == null)
                throw new Exception();
            if (rawProducts.isEmpty()) {
                return "There ara no data for import";
            }

            String headersString = getHeadersFromCSV().stream().collect(Collectors.joining(";")).toString();
            productToCsv = new FileWriter(fileConnection.getFilePath(), true);
            for (RawProduct rawProduct : rawProducts)
                productToCsv.write(rawProduct.toCSVString(";"));

            productToCsv.close();
        } catch (Exception e) {
            try {
                productToCsv.close();
            } catch (IOException ioException) {
                return "Error: Can't close the file!";
            }
        }
        return "Data were imported successfully!";
    }

    @Override
    public void deleteProduct(Product product) {
        List<RawProduct> updatedRawProducts = new ArrayList<>();
        try {
            if (product == null)
                throw new Exception();
            CsvToBean<RawProduct> csvToBean = getCSVParser();
            List<RawProduct> products = csvToBean.parse();
            updatedRawProducts = products.stream().map
                    (x -> new ProductAdapter(x).getProduct())
                    .filter(x -> !x.getTitle().equals(product.getTitle()))
                    .map(x -> new ProductAdapter(x).setDataToRawProduct()).collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error: Can't get the data! Try again!");
            return;
        }
        addRawProductsFromListToCSV(updatedRawProducts);
    }

    @Override
    public void modifyProduct(Product before, Product after) {
        List<RawProduct> updatedRawProducts = new ArrayList<>();
        try {
            if (before == null || after == null)
                throw new Exception();
            CsvToBean<RawProduct> csvToBean = getCSVParser();
            List<RawProduct> products = csvToBean.parse();

            for (RawProduct product : products) {
                if (product.getTitle().equals(before.getTitle()) &&
                        !product.equals(after)) {
                    updatedRawProducts.add(new ProductAdapter(after).setDataToRawProduct());
                    System.out.println(String.format("You have modified an old product\n %s\n" +
                            "to a new product\n %s\n", before, after));
                } else {
                    updatedRawProducts.add(product);
                }
            }
            addRawProductsFromListToCSV(updatedRawProducts);
        } catch (Exception e) {
            System.err.println("Error: Can't get the data! Try again!");
        }
    }

    @Override
    public void showAllProducts() {
        try {
            CsvToBean<RawProduct> csvToBean = getCSVParser();
            List<RawProduct> products = csvToBean.parse();
            for (RawProduct product : products) {
                ProductAdapter productAdapter = new ProductAdapter(product);
                System.out.println(productAdapter.getProduct().showInfo());
            }
        } catch (Exception e) {
            System.err.println("Error: Can't get the data! Try again!");
        }
    }

    public Product getProductByTitle(String title) {
        try {
            CsvToBean<RawProduct> csvToBean = getCSVParser();
            List<RawProduct> products = csvToBean.parse();
            return products.stream().map(x -> new ProductAdapter(x).getProduct())
                    .filter(x -> x.getTitle().equals(title))
                    .findAny()
                    .orElse(null);

        } catch (Exception e) {
            System.err.println("Error: Can't get the data! Try again!");
            return null;
        }
    }

    public CsvToBean<RawProduct> getCSVParser() throws FileNotFoundException {
        List<String> headersFromClass = new ReflectProductUtils().getFieldsFromClass(new Product());
        List<String> headersFromCSV = getHeadersFromCSV();

        Map mapping = IntStream.range(0, headersFromCSV.size())
                .boxed()
                .collect(Collectors.toMap(headersFromCSV::get, headersFromClass::get));

        CsvToBean csv = null;

        var strategy = new HeaderColumnNameTranslateMappingStrategy<RawProduct>();

        strategy.setColumnMapping(mapping);
        strategy.setType(RawProduct.class);

        CsvToBean<RawProduct> csvToBean = new CsvToBeanBuilder<RawProduct>(
                new FileReader(fileConnection.getFilePath())).withType(RawProduct.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withMappingStrategy(strategy)
                .build();

        return csvToBean;
    }

    public List<Product> getDataForReportFromCSV(String header, String criteries) {
        List<Product> products = new ArrayList<>();

        try {
            CsvToBean<RawProduct> csvToBean = getCSVParser();

            List<RawProduct> rawProducts = csvToBean.parse();
            String fieldName;
            PropertyDescriptor pd;

            for (RawProduct product : rawProducts) {
                pd = new PropertyDescriptor(header, product.getClass());

                fieldName = pd.getReadMethod().invoke(product).toString().trim().toUpperCase();
                if (fieldName.contains(criteries)) {
                    ProductAdapter productAdapter = new ProductAdapter(product);
                    products.add(productAdapter.getProduct());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't find the database file");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Unknown error. Try to get correct information from database!");
        }

        return products;
    }
}
