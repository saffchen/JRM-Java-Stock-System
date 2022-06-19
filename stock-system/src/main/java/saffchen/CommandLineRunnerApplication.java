package saffchen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import saffchen.command.Command;
import saffchen.command.CommandHolder;
import saffchen.utils.FileUtils;

import java.util.Map;
import java.util.Scanner;

@Component
@Profile("!test")
public class CommandLineRunnerApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        CommandHolder holder = new CommandHolder();

        Scanner scanner = new Scanner(System.in);
        System.out.println(FileUtils.getBanner());
        System.out.println(FileUtils.getParticipants());

        holder.printCommandInfo();
        String inputCommand = "";
        while (true) {
            try {
                System.out.print("Enter the command: ");
                inputCommand = scanner.next().trim().toUpperCase();
                for (Map.Entry<String, Command> entry : holder.getCommandHolder().entrySet()) {
                    if (inputCommand.equals(entry.getKey())) {
                        entry.getValue().doCommand();
                    }
                }
            } catch (Exception e) {
                System.out.println("Unsupported operation \"" + inputCommand + "\". Try again.");
            }
        }
    }
}
