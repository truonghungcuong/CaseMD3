package com.codegym.service.PartAdmin;

import com.codegym.dao.partAdmin.IPartAdminDAO;
import com.codegym.dao.partAdmin.PartAdminDAO;
import com.codegym.model.Part;
import com.codegym.model.PartImage;

import java.util.List;

public class PartAdminService implements IPartAdminService{
    IPartAdminDAO partAdminDAO = new PartAdminDAO();

    @Override
    public List<Part> findAllPart(int storyId1) {
        return partAdminDAO.findAllPart(storyId1);
    }

    @Override
    public Part findPartById(int partId) {
        return partAdminDAO.findPartById(partId);
    }

    @Override
    public boolean updatePart(int partId, Part part) {
        return partAdminDAO.updatePart(partId, part);
    }

    @Override
    public List<PartImage> selectAllPartOfStory(int storyId, int partId) {
        return partAdminDAO.selectAllPartOfStory(storyId, partId);
    }
}