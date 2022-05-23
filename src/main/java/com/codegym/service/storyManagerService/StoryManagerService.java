package com.codegym.service.storyManagerService;

import com.codegym.dao.storyManager.IStoryManagerDAO;
import com.codegym.model.Story;
import com.codegym.service.storyManagerService.IStoryManagerService;

import java.util.List;

public class StoryManagerService implements IStoryManagerService {


    private IStoryManagerDAO storyManagerDAO;

    public StoryManagerService(IStoryManagerDAO storyManagerDAO) {
        this.storyManagerDAO = storyManagerDAO;
    }

    @Override
    public List<Story> findAll() {
        return storyManagerDAO.findAll();
    }

    @Override
    public Story findById(int id) {
        return storyManagerDAO.findById(id);
    }

    @Override
    public boolean create(Story story) {
        return storyManagerDAO.create(story);
    }

    @Override
    public boolean updateById(int id, Story story) {
        return storyManagerDAO.updateById(id,story);
    }

    @Override
    public boolean deleteById(int id) {
        return storyManagerDAO.deleteById(id);
    }
    @Override
    public List<Story> findAllStoryByName(String name) {
        name = "%" + name + "%";
        return storyManagerDAO.findAllStoryByName(name);
    }

    @Override
    public List<Story> findAllProductByCategoryId(int categoryId) {
        return storyManagerDAO.findAllStoryByCategoryId(categoryId);
    }
}
