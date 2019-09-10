package pe.isil.dao;

import pe.isil.pe.isil.business.User;
import pe.isil.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
public class UserDAO {

    public static User isValidLogin(String login, String password) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM t_users WHERE login=? and password=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = getUser(resultSet);
                        return user;
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static List<User> findAll(){
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM t_users ";
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)){
                    while(resultSet.next()){
                        User user = getUser(resultSet);
                        users.add(user);
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return users;
    }

    public static User create(User user){
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO t_users (login, password) values (?, ?) ";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                int id = statement.executeUpdate();
                user.setId(id);
                return user;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static User getUser(ResultSet resultSet) throws SQLException {
        User user  = new User(
                resultSet.getInt("id"),
                resultSet.getString("login"),
                resultSet.getString("password"));
        return user;
    }

}
