package saffchen.command;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import saffchen.product.Product;
import saffchen.reports.PDFReportFromFileBySatellite;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static saffchen.export_excel.Create_Excel.SPEADSHEET_ID;
import static saffchen.export_excel.Create_Excel.getSheetsService;

public class ReceiverDB {

    public void addProduct(Product product) {
        System.out.println("Adding the product...");
    }

    public void modifyProduct() {
        System.out.println("Modified the product...");
    }

    public void deleteProduct() {
        System.out.println("Deleting the product...");
    }

    public void showAll() {
        System.out.println("Selecting the product...");
    }

    public void createReport() {
        Scanner scanSatellite = new Scanner(System.in);
        String criteria;
        while(true){
            System.out.println("*** REPORT ***");
            System.out.print("Enter the satellite or EXIT: ");
            criteria = scanSatellite.next().trim().toUpperCase();
            if (criteria.equals("EXIT"))
                break;
            try {
                PDFReportFromFileBySatellite report = new PDFReportFromFileBySatellite(criteria);
                report.generateReport();
            } catch (Exception e){
                System.out.println("Error: Can't create the report! Try again!");
            }
        }
    }
    // Сформировать .xls отчет
    public void createXLS() throws GeneralSecurityException, IOException {
        System.out.println("Укажите название файла");
        System.out.println("Имя файла не должно содержать символы: /, |, ?, *, :, <>");
        String name = new Scanner(System.in).nextLine();
        while (name.contains("?") || name.contains("/") || name.contains(":") || name.contains("*") ||
                name.contains("\"") || name.contains("<") || name.contains(">")||  name.contains("|") || name.contains("\\"))
        {
            System.out.println("Имя файла содержит запрещенные символы");
            System.out.println("Введите имя");
            name = new Scanner(System.in).nextLine();
        }
        String path = System.getProperty("user.home")+ "\\Desktop\\" +name+ ".xls";
        Sheets sheetsService = getSheetsService();
        String range = "Sheet1!A1:Z1000";
        ValueRange response = sheetsService.spreadsheets().values().get(SPEADSHEET_ID, range).execute();
        List<List<Object>> values = response.getValues();
        Workbook book = new HSSFWorkbook();
        Sheet sheetONE = book.createSheet("Sheet1");
        List<Row> rows = new ArrayList<>();
        int i = 0;
        for (List <Object> rower : values)
        {
            rows.add(sheetONE.createRow(i));
            for(int j = 0; j < rower.size(); j++)
            {
                rows.get(i).createCell(j).setCellValue(rower.get(j).toString());
            }
            i++;
        }
        book.write(new FileOutputStream(path));
        book.close();
    }
}
