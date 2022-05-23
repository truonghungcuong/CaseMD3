package com.codegym.dao.partAdmin;

import com.codegym.dao.DBConnection;
import com.codegym.model.Part;
import com.codegym.model.PartImage;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartAdminDAO implements IPartAdminDAO {
    public static final String SELECT_FROM_PART_COMIC = "SELECT * FROM part JOIN contentImage ON part.id = contentImage.partId WHERE part.storyId =?";
    public static final String SELECT_FROM_PART_WHERE_STORY_ID = "SELECT * FROM part WHERE storyId = ?";
    public static final String UPDATE_PART_SET_STORY_ID_CATEGORY_ID_EPISODE_NAME_CONTENT_WHERE_ID = "UPDATE part SET storyId = ?, categoryId = ?, episode = ?, name = ?, content = ? WHERE id = ?";
    public static final String SELECT_FROM_PART_WHERE_ID = "SELECT * FROM part WHERE id = ?";
    DBConnection dbConnection = new DBConnection();
    Connection connection = dbConnection.getConnection();

    @Override
    public List<Part> findAllPart(int storyId1) {
        List<Part> result = new ArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PART_WHERE_STORY_ID);
            preparedStatement.setInt(1, storyId1);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int storyId = rs.getInt("storyId");
                int categoryId = rs.getInt("categoryId");
                int episode = rs.getInt("episode");
                String name = rs.getString("name");
                String content = rs.getString("content");
                result.add(new Part(id, storyId, categoryId, episode, name, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Part findPartById(int partId) {
        Part part = new Part();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(SELECT_FROM_PART_WHERE_ID);
            prepareStatement.setInt(1, partId);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int storyId = rs.getInt("storyId");
                int categoryId = rs.getInt("categoryId");
                int episode = rs.getInt("episode");
                String name = rs.getString("name");
                String content = rs.getString("content");
                part = new Part(id, storyId, categoryId, episode, name, content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return part;
    }

    @Override
    public boolean updatePart(int partId, Part part) {
        boolean rowUpdated = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PART_SET_STORY_ID_CATEGORY_ID_EPISODE_NAME_CONTENT_WHERE_ID);
            preparedStatement.setInt(1, part.getStoryID());
            preparedStatement.setInt(2, part.getCategoryId());
            preparedStatement.setInt(3, part.getEpisode());
            preparedStatement.setString(4, part.getName());
            preparedStatement.setString(5, part.getContent());
            preparedStatement.setInt(6, partId);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public List<PartImage> selectAllPartOfStory(int storyId, int partId) {
        List<PartImage> partImages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contentImage WHERE storyId = ? AND partId = ?");
            preparedStatement.setInt(1, storyId);
            preparedStatement.setInt(2, partId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String img = rs.getString("img");
                partImages.add(new PartImage(id,storyId,partId,img));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partImages;
    }

    @Override
    public boolean deleteImgById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete from contentimage where id = ?; ");
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void insertNewPart(Part part) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into part(storyId,categoryId,episode,name,content)values(?,?,?,?,?);");
           preparedStatement.setInt(1,part.getStoryID());
            preparedStatement.setInt(2,part.getCategoryId());

            preparedStatement.setInt(3,part.getEpisode());
            preparedStatement.setString(4,part.getName());
            preparedStatement.setString(5,part.getContent());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deletePart(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM PART WHERE ID = ?");
            statement.setInt(1,id);
        return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}