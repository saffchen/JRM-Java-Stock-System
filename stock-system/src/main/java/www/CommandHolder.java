package www;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {
    public Map<String, Command> getCommandHolder() {
        return commandHolder;
    }

    private Map<String, Command> commandHolder = new HashMap<>();
    public void addCommand(String key, Command command){
        try{
            commandHolder.put(key, command);
        }catch (Exception e){
            System.out.println("Error: Can't add the command");
        }
    }
}
