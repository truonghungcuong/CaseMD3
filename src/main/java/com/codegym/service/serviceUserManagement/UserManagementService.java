package com.codegym.service.serviceUserManagement;

import com.codegym.dao.usermanagement.UserManagementDAO;
import com.codegym.model.User;

import java.util.List;

public class UserManagementService implements IUserManagementService {
    private UserManagementDAO userManagementDAO;

    public UserManagementService(UserManagementDAO userManagementDAO) {
        this.userManagementDAO = userManagementDAO;
    }

    @Override
    public List<User> findAll() {
        return userManagementDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return userManagementDAO.findById(id);
    }

    @Override
    public boolean create(User user) {
        return userManagementDAO.create(user);
    }

    @Override
    public boolean updateById(int id, User user) {
        return userManagementDAO.updateById(id,user);
    }

    @Override
    public boolean deleteById(int id) {
        return userManagementDAO.deleteById(id);
    }
}