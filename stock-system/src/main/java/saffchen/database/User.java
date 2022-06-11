package saffchen.database;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String login;
    private String passHash;
    private Map<String, Boolean> authorized =
            new HashMap<>(Map.of("login", Boolean.FALSE, "password", Boolean.FALSE));

    public boolean checkPassword(String password) {

        String pass = password;

        if (password == null || password.isEmpty())
            return false;

        if (BCrypt.checkpw(pass, passHash)) {
            authorized.put("password", Boolean.TRUE);
            return true;
        } else {
            authorized.put("password", Boolean.FALSE);
            return false;
        }
    }

    public boolean checkTheLogin(String lgn) {
        if (lgn == null || lgn.isEmpty())
            return false;

        String login = lgn.trim().toUpperCase();

        if (this.login.toUpperCase().equals(login)) {
            this.authorized.put("login", Boolean.TRUE);
            return true;
        } else {
            this.authorized.put("login", Boolean.FALSE);
            return false;
        }
    }

    public boolean isAuthorized() {
        return authorized.get("password") && authorized.get("login");
    }

}
