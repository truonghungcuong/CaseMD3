package com.codegym.dao.user;

import com.codegym.model.User;

import java.util.List;

public interface IUserDAO {

    void register(User newUser);
    List<User> findAll();
    User findUserByUsernameAndPassword(String username, String password);
    boolean checkLogin(String username, String password);
}
