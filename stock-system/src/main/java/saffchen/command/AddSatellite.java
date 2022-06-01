package saffchen.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Locale;
import java.util.Scanner;

public class AddSatellite implements Command {

    @Override
    public String getInfo() {
        return "Write an \"add_satellite\" if you want to additional satellite";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {
        while (true) {
            System.out.println("Чтобы выйти из режима добавления склада введите команду exit");
            System.out.print("Введите название нового склада: ");
            String str = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT);
            if (str.equals("EXIT")) break;
            BufferedWriter writer = new BufferedWriter(new FileWriter("satellite.txt", true));
            writer.append("\n");
            writer.append(str);
            writer.close();
        }
    }
}