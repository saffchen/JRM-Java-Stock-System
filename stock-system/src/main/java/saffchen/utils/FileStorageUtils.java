package saffchen.utils;

import com.opencsv.*;
import com.opencsv.bean.*;
import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.product.ProductAdapter;
import saffchen.product.RawProduct;
import saffchen.product.ReflectProductUtils;

import java.beans.PropertyDescriptor;

import java.io.*;
import java.util.*;
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

    @Override
    public void addProduct(Product product) {
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

    private void addRawProductsFromListToCSV(List<RawProduct> rawProducts) {
        FileWriter productToCsv = null;
        try {
            String headersString = getHeadersFromCSV().stream().collect(Collectors.joining(";")).toString();
            productToCsv = new FileWriter(fileConnection.getFilePath(), false);
            productToCsv.write(headersString);
            for (RawProduct rawProduct : rawProducts)
                productToCsv.write(rawProduct.toCSVString(";"));

            productToCsv.close();
        } catch (Exception e) {
            System.err.println("Error: Can't write data");
            try {
                productToCsv.close();
            } catch (IOException ioException) {
                System.out.println("");
            }
        }
    }

    @Override
    public void deleteProduct(Product product) {
        List<RawProduct> updatedProducts = new ArrayList<>();
        try {
            CsvToBean<RawProduct> csvToBean = getCSVParser();
            List<RawProduct> products = csvToBean.parse();
            updatedProducts = products.stream().map
                    (x -> new ProductAdapter(x).getProduct())
                    .filter(x -> !x.getTitle().equals(product.getTitle()))
                    .map(x -> new ProductAdapter(x).setDataToRawProduct()).collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error: Can't get the data! Try again!");
        }

        FileWriter productToCsv = null;
        try {
            String headersString = getHeadersFromCSV().stream().collect(Collectors.joining(";")).toString();
            productToCsv = new FileWriter(fileConnection.getFilePath(), false);
            productToCsv.write(headersString);
            for (RawProduct rawProduct : updatedProducts) {
                productToCsv.write(rawProduct.toCSVString(";"));
            }
            productToCsv.close();

            System.out.println("Product was deleted from database successfully!");
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

    @Override
    public void modifyProduct(Product productBefore, Product productAfter) {
        List<RawProduct> tempRawProducts = new ArrayList<>();
        try {
            RawProduct rawProductBefore = new ProductAdapter(productBefore).setDataToRawProduct();
            RawProduct rawProductAfter = new ProductAdapter(productAfter).setDataToRawProduct();
            CsvToBean<RawProduct> csvToBean = getCSVParser();
            List<RawProduct> prdcts = csvToBean.parse();

            for (RawProduct prdct : prdcts) {
                if (!prdct.equals(rawProductBefore)) tempRawProducts.add(prdct);
                else tempRawProducts.add(rawProductAfter);
            }
            addRawProductsFromListToCSV(tempRawProducts);
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

    @Override
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

    private CsvToBean<RawProduct> getCSVParser() throws FileNotFoundException {
        List<String> headersFromClass = new ReflectProductUtils().getFieldsFromClass(new Product());
        List<String> headersFromCSV = getHeadersFromCSV();

        Map mapping = IntStream.range(0, headersFromCSV.size())
                .boxed()
                .collect(Collectors.toMap(headersFromCSV::get, headersFromClass::get));

        CsvToBean csv = null;

        HeaderColumnNameTranslateMappingStrategy<RawProduct> strategy = new HeaderColumnNameTranslateMappingStrategy<RawProduct>();

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

            List<RawProduct> prdcts = csvToBean.parse();
            String fieldName;
            PropertyDescriptor pd;

            for (RawProduct product : prdcts) {
                pd = new PropertyDescriptor(header, product.getClass());

                fieldName = pd.getReadMethod().invoke(product).toString().trim().toUpperCase();
                if (fieldName.contains(criteries)) {
                    ProductAdapter productAdapter = new ProductAdapter(product);
                    products.add(productAdapter.getProduct());
                    //System.out.println(product.getTitle() + ":" + product.getDescription() + ":" + product.getPrice());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't find the database file");
        } catch (IOException e) {
            System.out.println("Error: Can't read the database file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Unknown error. Try to get correct information from database!");
        }

        return products;
    }
}
