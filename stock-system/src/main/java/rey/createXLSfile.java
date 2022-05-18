package rey;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class createXLSfile
{
    private static final String APPLICATION_NAME = "STOCK_VALUES";
    private static final String SPEADSHEET_ID = "1Rdg7aHSejhaAtuHG9MDv_ktBL8cYFxlvBQJrcUBOBPk";
    // Авторизация
    private static Credential authorize() throws IOException, GeneralSecurityException
    {
        //авторизация по токену, который нужно создать в гугл акке
        InputStream in = createXLSfile.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.
                load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
        List<String> scopes = List.of(SheetsScopes.SPREADSHEETS);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory
                .getDefaultInstance(),clientSecrets,scopes)
                .setDataStoreFactory(new FileDataStoreFactory((new java.io.File("tokens"))))
                .setAccessType("offline").build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    // Гугл док сервис
    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    // Сформировать .xls отчет
    public static void createXLS() throws GeneralSecurityException, IOException {
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