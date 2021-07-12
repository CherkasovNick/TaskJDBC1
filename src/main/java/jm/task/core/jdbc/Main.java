package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        User user1 = new User("Rick", "Grimes", (byte)45);
        User user2 = new User("Cory", "Taylor", (byte)43);
        User user3 = new User("Joe", "Fender", (byte)81);
        User user4 = new User("Oliver", "Sykes", (byte)35);

        UserDao a = new UserDaoJDBCImpl();
        Util util = new Util();
        Connection db = util.getConnection();

        db.setAutoCommit(false);
        a.createUsersTable();
        db.commit();
        a.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User \'Rick\' added to the table;");
        a.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User \'Cory\' added to the table;");
        a.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User \'Joe\' added to the table;");
        a.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        db.commit();
        System.out.println("User \'Oliver\' added to the table;");

        List<User> list = a.getAllUsers();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        a.cleanUsersTable();
        a.dropUsersTable();
    }
}
