package saffchen;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import saffchen.database.DBConnection;

@SpringBootApplication
public class MenuRunner {
    public static void main(String[] args) {
        SpringApplication.run(MenuRunner.class, args);
        //DBConnection pgConnection = new DBConnection();
        //Session session = pgConnection.pgConnect();
    }
}
