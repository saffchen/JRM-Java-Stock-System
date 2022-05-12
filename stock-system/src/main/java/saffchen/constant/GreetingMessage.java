package saffchen.constant;

public class GreetingMessage {
    public static String greetingMessage = "Welcome to the Stock System \n";
    public static String commandExit = "Write an \"exit\" to exit the application";
    public static String commandModify_Product = "Write an \"modify_product\" if you want to make changes";
    public static String commandDelete_Product = "Write an \"delete_product\" if you want to delete product";
    public static String commandShow_All = "Write an \"show_all\" if you want to view all positions";
    public static String commandImport_Csv = "Write an \"import_csv\" if you want to save change to csv";
    public static String commandImport_Excel = "Write an \"import_csv\" if you want to save change to excel";
    public static String commandImport_Gsheets = "Write an \"import_csv\" if you want to save change to google sheets";
    public static String commandGenerateReport = "Write an \"generate_report\" if you want to save pdf file with all positions \n";

    public void greetingMessages() {
        print(greetingMessage);
        print(commandExit);
        print(commandModify_Product);
        print(commandDelete_Product);
        print(commandShow_All);
        print(commandImport_Csv);
        print(commandImport_Excel);
        print(commandImport_Gsheets);
        print(commandGenerateReport);
    }

    public void print(String text) {
        System.out.println(text);
    }
}
