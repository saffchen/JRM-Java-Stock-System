package saffchen.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author saffchen on 6/5/2022
 * @project JRM-Java-Stock-System
 */
public class DeleteSatellite implements Command {
    private static final Logger logger
            = LoggerFactory.getLogger(AddCommand.class);

    @Override
    public String getInfo() {
        return "Write an \"delete_satellite\" if you want to delete satellite";
    }

    @Override
    public void doCommand() throws GeneralSecurityException, IOException {
        logger.info(" --- DELETE_SATELLITE --- ");
        System.out.println("Введите exit для того, чтобы выйти в главное меню");
        System.out.print("Введите название склада, который необходимо удалить: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
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
        logger.info(" --- DELETE_SATELLITE --- {{}}" ,str);
        Files.move(tempFile, inputFile, StandardCopyOption.REPLACE_EXISTING);
    }
}