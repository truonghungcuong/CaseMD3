package com.codegym.service.PartAdmin;

import com.codegym.model.Part;
import com.codegym.model.PartImage;

import java.util.List;

public interface IPartAdminService {
    List<Part> findAllPart(int storyId1);

    Part findPartById(int partId);

    boolean updatePart(int partId, Part part);

    List<PartImage> selectAllPartOfStory(int storyId, int partId);
}