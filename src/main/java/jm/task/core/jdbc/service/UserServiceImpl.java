package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    Util util = new Util();

    public UserServiceImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("CREATE TABLE usersjm(id INT NOT NULL AUTO_INCREMENT, name VARCHAR (20) NOT NULL, " +
                    "lastname VARCHAR (20) NOT NULL, age INT, PRIMARY KEY (ID))");
        } catch (SQLException throwables) {

        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("DROP TABLE usersjm");
        } catch (SQLException throwables) {

        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("INSERT INTO usersjm(name, age) VALUES (\'" + name + "\', \'" + lastName + "\', " + age + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("DELETE FROM usersjm WHERE id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        String query = "SELECT * FROM usersjm";
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("DELETE FROM usersjm");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
