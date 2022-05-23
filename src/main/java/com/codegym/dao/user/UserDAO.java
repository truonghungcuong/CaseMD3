package com.codegym.dao.user;

import com.codegym.dao.DBConnection;
import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/caseStudy?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "thuthuyda1";

    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void register(User newUser) {
        User user = null;
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user(username,password,fullName,email,address,phone)VALUES(?,?,?,?,?,?) ");
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getPassword());
            statement.setString(3, newUser.getFullName());
            statement.setString(4, newUser.getEmail());
            statement.setString(5, newUser.getAddress());
            statement.setString(6, newUser.getPhone());

            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from user");
             ResultSet rs = statement.executeQuery();

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String username = rs.getString("username");
                 String password = rs.getString("password");

                 String fullName= rs.getString("fullName");
                boolean role = rs.getBoolean("role");
                 String email= rs.getString("email");
                  String img =rs.getString("img");
                  String address = rs.getString("address");
                  String phone = rs.getString("phone");
                  users.add(new User(id,username,password,fullName,role,email,img,address,phone));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        List<User> users = findAll();
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                index = i;
            }
        }
        User user = users.get(index);
        return user;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        boolean checkLogin = false;
        List<User> users = findAll();
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                checkLogin = true;
            }
        }
        return checkLogin;
    }


}
