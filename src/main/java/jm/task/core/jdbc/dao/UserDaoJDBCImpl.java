package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Util UTIL = new Util();
    private static final Connection CONNECTION = UTIL.getConnection();
    private static final String CREATE = "CREATE TABLE IF NOT EXISTS users (id int AUTO_INCREMENT NOT NULL, name varchar(50), lastname varchar(50), age int(3), PRIMARY KEY (id));";
    private static final String DROP = "DROP TABLE IF EXISTS users";
    private static final String SAVE = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String CLEAN_TABLE = "TRUNCATE TABLE users";

    public void createUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(CREATE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(DROP);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(SAVE)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем - " + name + "добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL);
            while (true) {
                try {
                    if (!rs.next()) break;
                    Long id = (long) rs.getInt(1);
                    String name = rs.getString(2);
                    String lastname = rs.getString(3);
                    byte age = rs.getByte(4);
                    users.add(new User(id, name, lastname, age));
                } catch (SQLException ignore) {

                }
            }
        } catch (SQLException ignore) {
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(CLEAN_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
