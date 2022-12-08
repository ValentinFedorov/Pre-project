package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3307/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final SessionFactory SESSION_FACTORY = null;

    public Connection getConnection() {
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

    public SessionFactory getSession() {
        if (SESSION_FACTORY == null) {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", URL);
            prop.setProperty("hibernate.connection.username", USERNAME);
            prop.setProperty("hibernate.connection.password", PASSWORD);
            prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            SessionFactory sessionFactory = new Configuration().addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
        }
        return SESSION_FACTORY;
    }
}
