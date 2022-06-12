package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.database.Authorization;
import saffchen.database.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class AddSatellite implements Command {
    private static User authUser = null;
    private static final Authorization authorization = new Authorization();
    private Exit exit;

    private void setExit(Exit exit) {
        this.exit = exit;
    }

    private static final Logger logger
            = LoggerFactory.getLogger(AddCommand.class);

    @Override
    public String getInfo() {
        return "Write an \"add_satellite\" if you want to additional satellite";
    }

    public void isAuthorizedSuccessfully() {
        if (AddSatellite.authUser == null) {
            Scanner creds = new Scanner(System.in);
            boolean isFailed = true;
            try {

                for (int i = 0; i < Authorization.ATTEMPT_COUNT; i++) {

                    System.out.println("Enter login and password (Attempt count = " + (Authorization.ATTEMPT_COUNT - i) + ")");
                    System.out.print("login: ");
                    String login = creds.nextLine().trim().toLowerCase();
                    if (login.equals("exit")) {
                        setExit(new ExitFromCommandMenu());
                        exit.doExit();
                    }
                    System.out.print("Enter the password: ");
                    String password = creds.nextLine();

                    AddSatellite.authUser = authorization.authorize(login, password);
                    if (AddSatellite.authUser == null)
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
    }

    @Override
    public void doCommand() throws Exception {
        logger.info(" --- ADD_SATELLITE --- ");

        isAuthorizedSuccessfully();

        while (true) {
            System.out.println("Чтобы выйти из режима добавления склада введите команду exit");
            System.out.print("Введите название нового склада: ");
            String str = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT);
            if (str.equals("EXIT")) {
                setExit(new ExitFromCommandMenu());
                exit.doExit();
            }
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("satellite.txt", true));
                writer.append("\n");
                writer.append(str);
                writer.close();
                logger.info(" --- ADD_SATELLITE --- {{}}", str);
                System.out.printf("Success! Satellite %s was added%n", str);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                assert writer != null;
                writer.close();
            }
        }
    }
}