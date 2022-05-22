package saffchen.command;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static saffchen.export_excel.CreateExcel.SPEADSHEET_ID;
import static saffchen.export_excel.CreateExcel.getSheetsService;

public class CreateXlsFileCommand implements Command {

    public void createXLS() throws GeneralSecurityException, IOException {
        System.out.println("Укажите путь, где будет храниться файл");
        String filePath = new Scanner(System.in).nextLine();
        System.out.println("Укажите название файла");
        System.out.println("Имя файла не должно содержать символы: /, |, ?, *, :, <>");
        String name = new Scanner(System.in).nextLine();
        while (name.contains("?") || name.contains("/") || name.contains(":") || name.contains("*") ||
                name.contains("\"") || name.contains("<") || name.contains(">") || name.contains("|") || name.contains("\\")) {
            System.out.println("Имя файла содержит запрещенные символы");
            System.out.println("Введите имя");
            name = new Scanner(System.in).nextLine();
        }
        String path = filePath + name + ".xls";
        Sheets sheetsService = getSheetsService();
        String range = "Sheet1!A1:Z1000";
        ValueRange response = sheetsService.spreadsheets().values().get(SPEADSHEET_ID, range).execute();
        List<List<Object>> values = response.getValues();
        Workbook book = new HSSFWorkbook();
        Sheet sheetONE = book.createSheet("Sheet1");
        List<Row> rows = new ArrayList<>();
        int i = 0;
        for (List<Object> rower : values) {
            rows.add(sheetONE.createRow(i));
            for (int j = 0; j < rower.size(); j++) {
                rows.get(i).createCell(j).setCellValue(rower.get(j).toString());
            }
            i++;
        }
        book.write(new FileOutputStream(path));
        book.close();
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {
        createXLS();
        }
    }