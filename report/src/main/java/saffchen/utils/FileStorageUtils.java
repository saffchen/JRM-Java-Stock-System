package saffchen.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import saffchen.database.FileConnection;
import saffchen.dto.ProductDtoReport;
import saffchen.entities.ProductEntity;
import saffchen.product.ProductAdapter;
import saffchen.product.ProductAdapterReport;
import saffchen.product.RawProduct;
import saffchen.product.ReflectProductUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileStorageUtils {
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

    public CsvToBean<RawProduct> getCSVParser() throws FileNotFoundException {
        List<String> headersFromClass = new ReflectProductUtils().getFieldsFromClass(new ProductDtoReport());
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

    public List<ProductDtoReport> getDataForReportFromCSV(String header, String criteries) {
        List<ProductDtoReport> products = new ArrayList<>();

        try {
            CsvToBean<RawProduct> csvToBean = getCSVParser();

            List<RawProduct> rawProducts = csvToBean.parse();
            String fieldName;
            PropertyDescriptor pd;

            for (RawProduct product : rawProducts) {
                pd = new PropertyDescriptor(header, product.getClass());

                fieldName = pd.getReadMethod().invoke(product).toString().trim().toUpperCase();
                if (fieldName.contains(criteries)) {
                    ProductAdapterReport productAdapter = new ProductAdapterReport(product);
                    products.add(productAdapter.getProduct());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't find the database file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Unknown error. Try to get correct information from database!");
        }

        return products;
    }
}
