package com.codegym.dao.storyManager;

import com.codegym.dao.IGeneralDAO;
import com.codegym.model.Story;

import java.util.List;

public interface IStoryManagerDAO extends IGeneralDAO<Story> {
    List<Story> findAllStoryByName(String name);
    List<Story> findAllStoryByCategoryId (int categoryId);
}
