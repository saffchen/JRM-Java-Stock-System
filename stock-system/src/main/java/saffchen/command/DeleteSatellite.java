package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.database.Authorization;
import saffchen.database.User;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author saffchen on 6/5/2022
 * @project JRM-Java-Stock-System
 */
public class DeleteSatellite implements Command {
    private static User authUser = null;
    private static final Authorization AUTHORIZATION = new Authorization();
    private Exit exit;

    private void setExit(Exit exit) {
        this.exit = exit;
    }

    private static final Logger LOGGER
            = LoggerFactory.getLogger(AddCommand.class);

    @Override
    public String getInfo() {
        return "Write an \"delete_satellite\" if you want to delete satellite";
    }

    @Override
    public void doCommand() throws Exception {
        LOGGER.info(" --- DELETE_SATELLITE --- ");

        if (DeleteSatellite.authUser == null) {
            Scanner creds = new Scanner(System.in);
            boolean isFailed = true;
            try {

                for (int i = 0; i < Authorization.ATTEMPT_COUNT; i++) {

                    System.out.println("Enter login and password (Attempt count = " + (Authorization.ATTEMPT_COUNT - i) + ")");
                    System.out.print("login: ");
                    String login = creds.nextLine().trim().toLowerCase();
                    if (login.equals("exit")) {
                        setExit(new ExitFromCommandMenu());
                        exit.doSmth();
                    }
                    System.out.print("Enter the password: ");
                    String password = creds.nextLine();

                    DeleteSatellite.authUser = AUTHORIZATION.authorize(login, password);
                    if (DeleteSatellite.authUser == null)
                        System.out.println("Fail: Check login or password");
                    else {
                        System.out.println("Successful!");
                        isFailed = false;
                        break;
                    }
                }
                if (isFailed)
                    return;
            } catch (Exception e) {
                System.out.println("Error: Authorization was broken!");
            }
        }

        System.out.println("Введите exit для того, чтобы выйти в главное меню");
        System.out.print("Введите название склада, который необходимо удалить: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        if (str.equals("exit")) {
            setExit(new ExitFromCommandMenu());
            exit.doSmth();
        }
        Path inputFile = Paths.get("satellite.txt");
        Path tempFile = Files.createTempFile("temp", ".txt");
        Stream<String> lines = Files.lines(inputFile);
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            lines
                    .filter(line -> !line.startsWith(str))
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        LOGGER.info(" --- DELETE_SATELLITE --- {{}}", str);
        Files.move(tempFile, inputFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println(String.format("Success! Satellite %s has been removed", str));
    }
}