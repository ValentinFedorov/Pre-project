package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Kseniya", "Boldyreva", (byte)24);
        service.saveUser("Valentin", "Fedorov", (byte)23);
        service.saveUser("Igor", "Pereverzev", (byte)32);
        service.saveUser("Natalya", "Fedorova", (byte)60);
        List<User> allUsers = service.getAllUsers();
        allUsers.forEach(System.out::println);
        service.cleanUsersTable();
        service.dropUsersTable();


    }
}
