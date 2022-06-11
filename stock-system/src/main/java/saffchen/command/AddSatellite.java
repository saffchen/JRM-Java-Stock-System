package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Locale;
import java.util.Scanner;

public class AddSatellite implements Command {
    private static final Logger logger
            = LoggerFactory.getLogger(AddSatellite.class);
    @Override
    public String getInfo() {
        return "Write an \"add_satellite\" if you want to additional satellite";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {
        logger.info(" --- ADD_SATELLITE ---");
        while (true) {
            System.out.println("Чтобы выйти из режима добавления склада введите команду exit");
            System.out.print("Введите название нового склада: ");
            String str = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT);
            if (str.equals("EXIT")) break;
            FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir"), "satellite.txt"));
            fileWriter.append("\n");
            fileWriter.append(str);
            fileWriter.close();
            logger.info(" --- ADD_SATELLITE --- {{}}", str);
        }
    }
}