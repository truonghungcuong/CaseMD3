package com.codegym.service.categoryManagerService;

import com.codegym.service.IGeneralService;
import com.codegym.model.Category;
import com.codegym.model.Story;

import java.util.List;


public interface ICategoryService extends IGeneralService <Category> {
    List<Category> findAllCategoryByName (String name);
}