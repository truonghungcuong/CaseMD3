package com.codegym.service.storyManagerService;

import com.codegym.service.IGeneralService;
import com.codegym.model.Story;

import java.util.List;

public interface IStoryManagerService extends IGeneralService <Story> {
    List<Story> findAllStoryByName (String name);
    List<Story> findAllProductByCategoryId(int categoryId);
}