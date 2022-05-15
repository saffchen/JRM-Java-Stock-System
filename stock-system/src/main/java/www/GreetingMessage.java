package www;

import java.util.Arrays;
import java.util.List;

public class GreetingMessage {
    private List<String> listOfMessages = Arrays.asList("Welcome to the Stock System",
            "*******************************************************************************\n",
            "1 Write an \"exit\" to exit the application",
            "2 Write an \"modify_product\" if you want to make changes",
            "3 Write an \"modify_product\" if you want to make changes",
            "4 Write an \"delete_product\" if you want to delete product",
            "5 Write an \"show_all\" if you want to view all positions",
            "6 Write an \"import_csv\" if you want to save change to csv",
            "7 Write an \"import_excel\" if you want to save change to excel",
            "8 Write an \"import_gsheets\" if you want to save change to google sheets",
            "9 Write an \"generate_report\" if you want to save pdf file with all positions\n",
            "*******************************************************************************\n"
    );

    public void addToListOfMessages(String message) {
        this.listOfMessages.add(message);
    }

    public void printGreetingMessages() {
        for (String message:this.listOfMessages) {
            System.out.println(message);
        }
    }
}

