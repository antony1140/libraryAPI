package com.antony1140.LibWebApp.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<User> getUsers() throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getUsers();
    }

    public static void createUser(User user) throws SQLException {
        UserDao userDao = new UserDao();
        userDao.insert(user);
    }

//    public int deleteUser(int id){
//
//    }


    public User login(String username, String password) throws SQLException {
        try {
            UserDao userDao = new UserDao();
            return userDao.getByLogin(username, password);
        }catch (SQLException e) {
            return null;
        }
    }
}
