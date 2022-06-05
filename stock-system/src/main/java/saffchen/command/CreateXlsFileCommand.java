package saffchen.command;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static saffchen.export_excel.CreateExcel.SPEADSHEET_ID;
import static saffchen.export_excel.CreateExcel.getSheetsService;

public class CreateXlsFileCommand implements Command {
    private static final List <String> DEPRECATED_SYMBOLS = List.of("/", "|", "?", "*", "<", ">", "!");

    @Override
    public String getInfo() {
        return "Write an \"export_excel\" if you want to save excel file with all positions";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {
        String name;
        do {
            System.out.println("Укажите название файла");
            System.out.println("Имя файла не должно содержать символы: " + DEPRECATED_SYMBOLS);
            name = new Scanner(System.in).nextLine();
        } while (!isNameCorrect(name));
        String path = name + ".xls";
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
        FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir"), path));
        System.out.println("Success! File saved: " + System.getProperty("user.dir"));
        fileWriter.close();
    }

    //проверяем исключения
    private boolean isNameCorrect(String name) {
        for (String deprecatedSymbol: DEPRECATED_SYMBOLS) {
            if (name.contains(deprecatedSymbol)) {
                System.err.println("Ошибка, введите название корректно!");
                return false;
            }
        }
        return true;
    }
}