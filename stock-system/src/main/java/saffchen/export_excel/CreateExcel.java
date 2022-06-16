package saffchen.export_excel;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import saffchen.command.CreateXlsFileCommand;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.List;

public class CreateExcel {
    private static final String APPLICATION_NAME = "STOCK_VALUES";
    public static final String SPEADSHEET_ID = "1Rdg7aHSejhaAtuHG9MDv_ktBL8cYFxlvBQJrcUBOBPk";

    private static Credential authorize() throws IOException, GeneralSecurityException {
        //авторизация по токену, который нужно создать в гугл акке
//        InputStream in = CreateXlsFileCommand.class.getResourceAsStream("/credentials.json");
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.
//                load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
//        List<String> scopes = List.of(SheetsScopes.SPREADSHEETS);
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
//                .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory
//                .getDefaultInstance(), clientSecrets, scopes)
//                .setDataStoreFactory(new FileDataStoreFactory((new java.io.File("tokens"))))
//                .setAccessType("offline").build();
//        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        return null;
    }

    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
