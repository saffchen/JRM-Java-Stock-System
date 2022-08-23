package saffchen.utils;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import saffchen.database.GSheetConnection;
import saffchen.product.RawProduct;
import saffchen.product.ReflectProductUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class GSheetImportUtils implements ImportUtils {
    private final Sheets service;

    public GSheetImportUtils(Sheets service) {
        this.service = service;
    }

    @Override
    public List<RawProduct> getData() {
        List<List<String>> values = getRowsFromGSheet();
        return getProductsFromRows(values);
    }

    private List<List<String>> getRowsFromGSheet() {
        List<List<String>> values = new ArrayList<>();
        try {
            ValueRange result = service.spreadsheets().values()
                    .get(GSheetConnection.SPREADSHEET_ID, GSheetConnection.RANGE)
                    .execute();
            values = result.getValues().stream()
                    .map(list -> list.stream()
                            .map(String::valueOf)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
            //remove the headers
            values.remove(0);
            if (values.isEmpty()) {
                System.out.println("No data found!");
            }
        } catch (IOException e) {
            System.out.println("Error: Can't get data from GSHEET");
        }
        return values;
    }

    private static List<RawProduct> getProductsFromRows(List<List<String>> rows) {
        List<RawProduct> products = new ArrayList<>();
        ReflectProductUtils reflectProductUtils = new ReflectProductUtils();
        List<String> headersFromClass = reflectProductUtils.getFieldsFromClass(RawProduct.class);
        for (List<String> row : rows) {
            Map<String, Object> mapping = IntStream.range(0, headersFromClass.size())
                    .boxed()
                    .collect(Collectors.toMap(headersFromClass::get, row::get));
            RawProduct rawProduct = new RawProduct();
            for (Map.Entry<String, Object> entry : mapping.entrySet()) {
                reflectProductUtils.invokeSetter(rawProduct, entry.getKey(), entry.getValue());
            }
            products.add(rawProduct);
        }
        return products;
    }
}
