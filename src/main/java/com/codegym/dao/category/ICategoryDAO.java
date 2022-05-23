package com.codegym.dao.category;

import com.codegym.dao.IGeneralDAO;
import com.codegym.model.Category;

import java.util.List;

public interface ICategoryDAO extends IGeneralDAO <Category> {
    List<Category> findAllCategoryByName(String name);
}
