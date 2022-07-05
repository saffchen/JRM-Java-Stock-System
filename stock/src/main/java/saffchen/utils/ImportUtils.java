package saffchen.utils;

import saffchen.product.RawProduct;

import java.util.ArrayList;
import java.util.List;

public interface ImportUtils {
    public default List<RawProduct> checkTheDublicates(List<RawProduct> gsheetData, List<RawProduct> csvData) {
        List<RawProduct> toImport = new ArrayList<>();
        try {

            if (gsheetData.isEmpty() || gsheetData == null || csvData.isEmpty() || csvData == null)
                throw new IllegalArgumentException();

            for (RawProduct fromGsheet : gsheetData) {
                if (!csvData.contains(fromGsheet))
                    toImport.add(fromGsheet);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: The arguments are not correсt!");
        } catch (Exception e) {
            System.out.println("Error: Can't import the data from gsheet to csv");
        }
        return toImport;
    }

    List<RawProduct> getData() throws Exception;
}
