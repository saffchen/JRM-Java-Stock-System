package saffchen.command;

import saffchen.database.Database;

public class ShowAllCommand implements Command{
    private Database database;

    public ShowAllCommand(Database database) {
        this.database = database;
    }

    @Override
    public void doCommand() {
        database.show_all();
    }
}
