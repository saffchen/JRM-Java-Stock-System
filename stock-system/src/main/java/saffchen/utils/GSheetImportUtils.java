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

import static java.util.stream.Collectors.toList;

public class GSheetImportUtils implements ImportUtils {
    private final String RANGE = GSheetConnection.RANGE;
    private Sheets service;

    public GSheetImportUtils(Sheets service) {
        this.service = service;
    }

    @Override
    public List<RawProduct> getData() {
        ValueRange result = null;
        List<List<String>> values = null;
        List<String> listOfStrProducts = new ArrayList<>();
        List<RawProduct> products = new ArrayList<>();
        try {
            result = service.spreadsheets().values().get(GSheetConnection.SPEADSHEET_ID, RANGE).execute();
            values = result.getValues().stream()
                    .map(list -> {
                        List<String> listOfString = list.stream()
                                .map(m -> String.valueOf(m))
                                .collect(Collectors.toList());
                        return listOfString;
                    })
                    .collect(Collectors.toList());

            //remove the headers
            values.remove(0);

            if (values == null || values.isEmpty()) {
                System.out.println("No data found!");
            } else {
                for (List<String> row : values) {
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
            products.add(rawProduct);
        }

        return products;
    }

}
