package com.codegym.dao.storyManager;

import com.codegym.dao.DBConnection;
import com.codegym.model.Story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoryManagerDAO implements IStoryManagerDAO {
    public static final String SELECT_FROM_STORY = "select * from Story";
    public static final String SELECT_FROM_STORY_WHERE_ID = "select * from story where id =?";
    public static final String INSERT_INTO_STORY = "INSERT INTO story (`categoryId`, `img`, `name`, `price`, `author`, `dateSubmitted`) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_STORY_SET = "update story set categoryId = ?, img = ? , name = ? , price = ?, author = ? , dateSubmitted = ? where id = ?";
    public static final String DELETE_FROM_STORY_WHERE_ID = "delete from story where id =?";
    public static final String SELECT_FROM_STORY_WHERE_NAME_LIKE = "select  * from story where name like ?";
    public static final String SELECT_FROM_STORY_WHERE_CATEGORY_ID = "select * from story where categoryId = ?";
    Connection connection = DBConnection.getConnection();

    public StoryManagerDAO() {
    }

    @Override
    public List<Story> findAll() {
        List<Story> stories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_STORY);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int categoryId = rs.getInt("categoryId");
                String img = rs.getString("img");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String writer = rs.getString("author");
                String dateSubmited = rs.getString("dateSubmitted");
                Story story = new Story(id, categoryId, img, name, price, writer, dateSubmited);
                stories.add(story);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stories;
    }

    @Override
    public Story findById(int id) {
        Story story = new Story();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_STORY_WHERE_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("categoryId");
                String img = rs.getString("img");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String writer = rs.getString("author");
                String dateSubmited = rs.getString("dateSubmitted");
                story = new Story(id, categoryId, img, name, (int) price, writer, dateSubmited);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return story;
    }

    @Override
    public boolean create(Story story) {
        boolean rowCreate = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_STORY);
            preparedStatement.setInt(1, story.getCategoryId());
            preparedStatement.setString(2, story.getImg());
            preparedStatement.setString(3, story.getName());
            preparedStatement.setDouble(4, story.getPrice());
            preparedStatement.setString(5, story.getWriter());
            preparedStatement.setString(6, story.getDateSubmited());
            rowCreate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCreate;
    }

    @Override
    public boolean updateById(int id, Story story) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STORY_SET);
            preparedStatement.setInt(1, story.getCategoryId());
            preparedStatement.setString(2, story.getImg());
            preparedStatement.setString(3, story.getName());
            preparedStatement.setDouble(4, story.getPrice());
            preparedStatement.setString(5, story.getWriter());
            preparedStatement.setString(6, story.getDateSubmited());
            preparedStatement.setInt(7, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_STORY_WHERE_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Story> findAllStoryByName(String name) {
        List<Story> stories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_STORY_WHERE_NAME_LIKE);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int categoryId = rs.getInt("categoryId");
                String img = rs.getString("img");
                String name1 = rs.getString("name");
                double price = rs.getDouble("price");
                String writer = rs.getString("author");
                String dateSubmited = rs.getString("dateSubmitted");
                Story story = new Story(id, categoryId, img, name1, (int) price, writer, dateSubmited);
                stories.add(story);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stories;
    }


    @Override
    public List<Story> findAllStoryByCategoryId(int categoryId) {
        List<Story> stories = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_STORY_WHERE_CATEGORY_ID);
            preparedStatement.setInt(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int categoryId1 = rs.getInt("categoryId");
                String img = rs.getString("img");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String writer = rs.getString("author");
                String dateSubmited = rs.getString("dateSubmitted");
                Story story = new Story(id, categoryId1, img, name, price, writer, dateSubmited);
                stories.add(story);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stories;
    }
}