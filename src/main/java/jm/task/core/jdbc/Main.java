package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        User user1 = new User("Rick", "Grimes", (byte)45);
        User user2 = new User("Cory", "Taylor", (byte)43);
        User user3 = new User("Joe", "Fender", (byte)81);
        User user4 = new User("Oliver", "Sykes", (byte)35);

        UserService a = new UserServiceImpl();

        a.createUsersTable();
        a.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        a.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        a.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        a.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> list = a.getAllUsers();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        a.cleanUsersTable();
        a.dropUsersTable();
    }
}
