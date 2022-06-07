package saffchen.database;

import saffchen.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Authorization {
    private User user;
    private List<User> users = new ArrayList<>();
    public static final int ATTEMPT_COUNT = 3;
    public Authorization(){
        users = FileUtils.getUsersFromFile("usercredentials.txt");
    }

    public User authorize(String login, String password){
        try {
            for (User usr : users){
                usr.checkTheLogin(login);
                usr.checkPassword(password);
                if (usr.isAuthorized())
                    return usr;

            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: Can't get the users from DB. The command \'Modify\' is not available!");
        }
        return null;
    }
}
