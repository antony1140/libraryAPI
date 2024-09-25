package com.antony1140.LibWebApp.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.antony1140.LibWebApp.DataBase.*;


public class UserDao {

    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = Database.getConnection();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setAdmin(rs.getBoolean("admin"));
            System.out.println(user);

            users.add(user);

        }

        connection.close();
        rs.close();

        return users;

    }

    public int insert(User user) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "INSERT into users (first_name, last_name, admin, username, password) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setBoolean(3, user.isAdmin());
        ps.setString(4, user.getUserName());
        ps.setString(5, user.getPassword());
        int result = ps.executeUpdate();
        sql = "select LAST_INSERT_ID()";
        PreparedStatement ps2 = connection.prepareStatement(sql);
        ResultSet rs = ps2.executeQuery();
        int id = 0;
        if(rs.next()){
            id = rs.getInt("LAST_INSERT_ID()");
        }
        else{
            System.out.println("something went wrong");
        }

        connection.close();
        ps.close();
        return id;
    }

    public User getById(int id) throws SQLException {
        User user = null;
        Connection connection = Database.getConnection();
        String sql = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            user = new User();
            user.setId(id);
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setAdmin(rs.getBoolean("admin"));

        }
        rs.close();
        connection.close();
        return user;
    }

    public User getByLogin(String username, String password) throws SQLException {
        User user = null;
        Connection connection = Database.getConnection();
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setAdmin(rs.getBoolean("admin"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
        }
        ps.close();
        connection.close();
        return user;
    }

    public int deleteUser(int id) throws SQLException {

            System.out.println(1);
            Connection connection = Database.getConnection();
            System.out.println(2);
            String sql = "DELETE FROM users WHERE user_id = ?";
            System.out.println(3);
            PreparedStatement ps = connection.prepareStatement(sql);
            System.out.println(4);
            ps.setInt(1, id);
            System.out.println(5);
            int result = ps.executeUpdate();
            System.out.println(6);
            connection.close();
            return result;


    }

    public int updateUser(User user) throws SQLException{
       Connection connection = Database.getConnection();
       String sql = "UPDATE users SET first_name = ?, last_name = ?, admin = ?, username = ?, password = ? WHERE user_id = ?";
       PreparedStatement ps = connection.prepareStatement(sql);
       ps.setString(1, user.getFirstName());
       ps.setString(2, user.getLastName());
       ps.setBoolean(3, user.isAdmin());
       ps.setString(4, user.getUserName());
       ps.setString(5, user.getPassword());
       ps.setInt(6, user.getId());
       int result = ps.executeUpdate();
       connection.close();
       return result;

    }

}
