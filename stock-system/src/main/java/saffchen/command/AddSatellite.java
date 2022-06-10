package saffchen.command;

import java.io.File;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Scanner;

public class AddSatellite implements Command {
    private Exit exit;

    private void setExit(Exit exit) {
        this.exit = exit;
    }

    @Override
    public String getInfo() {
        return "Write an \"add_satellite\" if you want to additional satellite";
    }

    @Override
    public void doCommand() throws Exception {
        while (true) {
            System.out.println("Чтобы выйти из режима добавления склада введите команду exit");
            System.out.print("Введите название нового склада: ");
            String str = new Scanner(System.in).nextLine().toUpperCase(Locale.ROOT);
            if (str.equals("exit")) {
                setExit(new ExitFromCommandMenu());
                exit.doSmth();
            }
            FileWriter fileWriter = new FileWriter(new File(System.getProperty("user.dir"), "satellite.txt"));
            fileWriter.append("\n");
            fileWriter.append(str);
            fileWriter.close();
        }
    }
}