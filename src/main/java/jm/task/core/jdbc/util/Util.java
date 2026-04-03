package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    protected static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/habsida_task_1";
        String user = "root";
        String password = "1234";

        try {
            Connection connection =
                    DriverManager.getConnection(url, user, password);
            System.out.println("Connection ON");
            System.out.println("Hello");
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
