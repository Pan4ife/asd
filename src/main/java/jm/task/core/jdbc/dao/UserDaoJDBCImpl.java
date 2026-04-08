package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(100) NOT NULL,\n" +
                    "lastName VARCHAR(100) NOT NULL,\n" +
                    "age TINYINT NOT NULL\n" +
                    ");";
            statement.execute(createTableSQL);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()){
        String dropUserTableSQL = "DROP TABLE IF EXISTS users";
        statement.execute(dropUserTableSQL);
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserSQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection conn = Util.getConnection();
             PreparedStatement insertStat = conn.prepareStatement(saveUserSQL)) {
            insertStat.setString(1, name);
            insertStat.setString(2, lastName);
            insertStat.setByte(3, age);
            insertStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUserByIdSQL = "DELETE FROM users WHERE id = ?;\n";
        try (Connection conn = Util.getConnection();
             PreparedStatement deleteStat = conn.prepareStatement(removeUserByIdSQL)){
            deleteStat.setLong(1, id);
            deleteStat.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String getAllUsersSQL = "SElECT * FROM users";
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(getAllUsersSQL);
             ResultSet rs = ps.executeQuery()){
            List <User> allUsers = new ArrayList<>();

            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                allUsers.add(user);
            }
            return allUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()){
            String cleanUsersTable = "TRUNCATE TABLE users";
            statement.execute(cleanUsersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
