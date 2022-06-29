package saffchen.database;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

public class GSheetConnection implements Connection {

    private static Properties properties = new Properties();
    public static String RANGE;
    private static String APPLICATION_NAME;
    public static String SPEADSHEET_ID;
    private static Credential authorize() throws IOException, GeneralSecurityException {
        //авторизация по токену, который нужно создать в гугл акке
        InputStream in = GSheetConnection.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.
                load(JacksonFactory.getDefaultInstance(),
                        new InputStreamReader(in));
        List<String> scopes = List.of(SheetsScopes.SPREADSHEETS);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory
                .getDefaultInstance(), clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory((new java.io.File("tokens"))))
                .setAccessType("offline")
                .build();
        return null;
    }

    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(
                GSheetConnection.class.getResourceAsStream("/gsheet.properties")));

        GSheetConnection.APPLICATION_NAME = properties.getProperty("APPLICATION_NAME");
        GSheetConnection.SPEADSHEET_ID = properties.getProperty("SPEADSHEET_ID");
        GSheetConnection.RANGE = properties.getProperty("RANGE");

        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
