package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();
    private final Connection db = util.getConnection();


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            db.setAutoCommit(false);
            Statement statement = util.getConnection().createStatement();
            statement.execute("CREATE TABLE usersjm(id INT NOT NULL AUTO_INCREMENT, name VARCHAR (20) NOT NULL, " +
                    "lastname VARCHAR (20) NOT NULL, age INT, PRIMARY KEY (ID))");
            db.commit();
        } catch (SQLException throwables) {

        }

    }

    public void dropUsersTable() {
        try {
            db.setAutoCommit(false);
            Statement statement = util.getConnection().createStatement();
            statement.execute("DROP TABLE usersjm");
            db.commit();
        } catch (SQLException throwables) {

        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            db.setAutoCommit(false);
            Statement statement = util.getConnection().createStatement();
            statement.execute("INSERT INTO usersjm(name, lastname, age) VALUES (\'" + name + "\', \'" + lastName + "\', " + age + ")");
            db.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            db.setAutoCommit(false);
            Statement statement = util.getConnection().createStatement();
            statement.execute("DELETE FROM usersjm WHERE id = " + id);
            db.commit();
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
            db.setAutoCommit(false);
            Statement statement = util.getConnection().createStatement();
            statement.execute("DELETE FROM usersjm");
            db.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
