package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.database.Authorization;
import saffchen.database.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Scanner;

public class AddSatellite implements Command {
    private static User authUser = null;
    private static final Authorization AUTHORIZATION = new Authorization();
    private static final Logger LOGGER
            = LoggerFactory.getLogger(AddCommand.class);
    public Exit exit;

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    @Override
    public String getInfo() {
        return "Write an \"add_satellite\" if you want to additional satellite";
    }

    @Override
    public void doCommand() throws Exception {
        LOGGER.info(" --- ADD_SATELLITE --- ");

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
                        exit.doSmth();
                    }
                    System.out.print("Enter the password: ");
                    String password = creds.nextLine();

                    AddSatellite.authUser = AUTHORIZATION.authorize(login, password);
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


            while (true) {
                System.out.println("Чтобы выйти из режима добавления склада введите команду exit");
                System.out.print("Введите название нового склада: ");
                String str = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT);
                if (str.equals("EXIT")) {
                    setExit(new ExitFromCommandMenu());
                    exit.doSmth();
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("satellite.txt", true));
                writer.append("\n");
                writer.append(str);
                writer.close();
                LOGGER.info(" --- ADD_SATELLITE --- {{}}", str);
                System.out.printf("Success! Satellite %s was added%n", str);
            }
        }
    }
}