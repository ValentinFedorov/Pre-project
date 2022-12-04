package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3307/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection () {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Драйвер не зарегистрировался");
        }
        return connection;
    }


}
