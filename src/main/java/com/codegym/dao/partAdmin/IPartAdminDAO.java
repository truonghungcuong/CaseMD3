package com.codegym.dao.partAdmin;

import com.codegym.model.Part;
import com.codegym.model.PartImage;

import java.util.List;

public interface IPartAdminDAO {
    List<Part> findAllPart(int storyId1);

    Part findPartById(int partId);

    boolean updatePart(int partId, Part part);

    List<PartImage>selectAllPartOfStory(int storyId, int partId);

    boolean deleteImgById(int id);
    void insertNewPart(Part part);
    boolean deletePart(int id);
}