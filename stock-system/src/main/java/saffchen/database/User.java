package saffchen.database;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String login;
    private String passHash;
    private boolean isAuthorized = false;

    private boolean checkPassowrd(String password) {
        String pass = password;
        if (password == null || password.isEmpty())
            return false;
        //String generatedSecuredPasswordHash = BCrypt.hashpw(pass, BCrypt.gensalt(10));
        return BCrypt.checkpw(pass, passHash);
    }

    public void setIsAuhorized(String password) {
        if (checkPassowrd(password))
            isAuthorized : isAuthorized = true;
        else
            isAuthorized = false;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }
}
