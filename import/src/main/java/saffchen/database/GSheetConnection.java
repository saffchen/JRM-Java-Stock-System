package saffchen.database;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import saffchen.utils.PropertiesLoader;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

public class GSheetConnection {

    private static final Properties properties = PropertiesLoader.load("gsheet.properties");
    private static final JacksonFactory JACKSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FILE = "credentials.json";
    private static final String TOKENS_DIRECTORY = "tokens";

    public static String APPLICATION_NAME;
    public static String SPREADSHEET_ID;
    public static String RANGE;

    private static Credential authorize(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        //авторизация по токену, который нужно создать в гугл аккаунте
        List<String> scopes = List.of(SheetsScopes.SPREADSHEETS);
        GoogleClientSecrets clientSecrets = getClientSecrets();
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JACKSON_FACTORY, clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory((new java.io.File(TOKENS_DIRECTORY))))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private static GoogleClientSecrets getClientSecrets() throws IOException {
        InputStream in = GSheetConnection.class.getClassLoader().getResourceAsStream(CREDENTIALS_FILE);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE);
        }
        InputStreamReader credentialsReader = new InputStreamReader(in);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JACKSON_FACTORY, credentialsReader);
        credentialsReader.close();
        in.close();
        return clientSecrets;
    }

    public static Sheets getSheetsService() throws IOException {
        final NetHttpTransport HTTP_TRANSPORT;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        GSheetConnection.APPLICATION_NAME = properties.getProperty("APPLICATION_NAME");
        GSheetConnection.SPREADSHEET_ID = properties.getProperty("SPREADSHEET_ID");
        GSheetConnection.RANGE = properties.getProperty("RANGE");

        return new Sheets.Builder(HTTP_TRANSPORT, JACKSON_FACTORY, authorize(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
