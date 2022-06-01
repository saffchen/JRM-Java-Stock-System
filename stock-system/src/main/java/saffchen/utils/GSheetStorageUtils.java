package saffchen.utils;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import saffchen.database.GSheetConnection;
import saffchen.product.Product;
import saffchen.product.ProductAdapter;
import saffchen.product.RawProduct;
import saffchen.product.ReflectProductUtils;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GSheetStorageUtils implements StorageUtils{
    private final String range = "Sheet1!A2:G";
    private Sheets service;

    public GSheetStorageUtils(Sheets service){
        this.service = service;
    }

    public List<Product> getDataFromGSheet(){
        ValueRange result = null;
        List<List<Object>> values = null;
        List<String> listOfStrProducts = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        try {
            result = service.spreadsheets().values().get(GSheetConnection.SPEADSHEET_ID, range) .execute();
            values = result.getValues();

            values.remove(0);
            if (values == null || values.isEmpty()){
                System.out.println("No data found!");
            }else {
                for (List row : values){
                    System.out.println(row);
                    listOfStrProducts.add((String) row.stream().collect(Collectors.joining(";")));
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Can't get data from GSHEET");
        }
        ReflectProductUtils reflectProductUtils = new ReflectProductUtils();
        List<String> headersFromClass = reflectProductUtils.getFieldsFromClass(new RawProduct());

            for (List row : values) {
                Map<String, Object> mapping = IntStream.range(0, headersFromClass.size())
                                                        .boxed()
                                                        .collect(Collectors.toMap(headersFromClass::get, row::get));
                RawProduct rawProduct = new RawProduct();
                for (Map.Entry<String, Object> entry : mapping.entrySet()) {
                    reflectProductUtils.invokeSetter(rawProduct, (String) entry.getKey(), (Object) entry.getValue());
                }
                products.add(new ProductAdapter(rawProduct).getProduct());
            }

        return products;
    }

    @Override
    public void addProduct(Product product) { /*NOP*/ }

    @Override
    public void deleteProduct(Product product) { /*NOP*/}

    @Override
    public void modifyProduct(Product before, Product after) { /*NOP*/ }

    @Override
    public void showAllProducts() { /*NOP*/ }

}
