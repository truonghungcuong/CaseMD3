package com.codegym.service.categoryManagerService;

import com.codegym.dao.category.ICategoryDAO;
import com.codegym.model.Category;

import java.util.List;

public class CategoryService implements ICategoryService {
    private ICategoryDAO categoryDAO;

    public CategoryService(ICategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public boolean updateById(int id, Category category) {
        return categoryDAO.updateById(id,category);
    }

    @Override
    public boolean deleteById(int id) {
        return categoryDAO.deleteById(id);
    }

    @Override
    public List<Category> findAllCategoryByName(String name) {
        name = "%" + name + "%";
        return categoryDAO.findAllCategoryByName(name);
    }
}