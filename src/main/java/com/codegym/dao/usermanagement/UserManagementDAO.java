package com.codegym.dao.usermanagement;

import com.codegym.dao.DBConnection;
import com.codegym.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManagementDAO implements IUserManagementDAO {
    Connection connection = DBConnection.getConnection();

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT*FROM user ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");
                boolean role = resultSet.getBoolean("role");
                String email = resultSet.getString("email");
                String img = resultSet.getString("img");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                User user = new User(id, username, password, fullName, role, email, img, address, phone);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");
                boolean role = resultSet.getBoolean("role");
                String email = resultSet.getString("email");
                String img = resultSet.getString("img");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                user = new User(id, username, password, fullName, role, email, img, address, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (username,password,fullname,role,email,img,address,phone) VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setBoolean(4, user.isRole());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getImg());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getPhone());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET username =?,password=?,fullname=?,role=?,email=?,img=?,address=?,phone=? WHERE id=?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setBoolean(4, user.isRole());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getImg());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getPhone());
            preparedStatement.setInt(9, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}