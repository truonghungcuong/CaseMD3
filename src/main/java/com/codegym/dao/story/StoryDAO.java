package com.codegym.dao.story;

import com.codegym.dao.DBConnection;
import com.codegym.model.Story;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryDAO implements IStoryDAO {

    DBConnection DBConnection = new DBConnection();
    Connection connection = DBConnection.getConnection();

    @Override
    public List<Story> selectAllStory() {
        String query = "{call select_all_story()}";
        List<Story> storyList = new ArrayList<>();

        try {
            CallableStatement statement = connection.prepareCall(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int categoryId = rs.getInt("categoryId");
                String img = rs.getString("img");
                String name = rs.getString("name");
                if (name.length() > 16){
                    name = name.substring(0,16) + "...";
                }
                int price = (int) rs.getDouble("price");
                String writer = rs.getString("author");
                String dateSubmited = rs.getString("dateSubmitted");

                storyList.add(new Story(id,categoryId,img,name,price,writer,dateSubmited));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storyList;
    }

    @Override
    public List<Story> selectByCategoryId(int categoryId) {
        List<Story> stories = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM story where categoryId = ?");
            preparedStatement.setInt(1,categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String img = rs.getString("img");
                String name = rs.getString("name");
                if (name.length() > 16){
                    name = name.substring(0,16) + "...";
                }
                int price = (int) rs.getDouble("price");
                String writer = rs.getString("author");
                String dateSubmited = rs.getString("dateSubmitted");
                stories.add( new Story(id,categoryId,img,name,price,writer,dateSubmited));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stories;
    }

    @Override
    public List<Story> selectByName(String name) {
        String query = "call select_by_name1(?)";
        List<Story> storyList = new ArrayList<>();

        try {
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int categoryId = rs.getInt("categoryId");
                String name1 = rs.getString("name");
                if (name.length() > 16){
                    name = name.substring(0,16) + "...";
                }
                String img = rs.getString("img");
                int price = (int) rs.getDouble("price");
                String writer = rs.getString("author");
                String dateSubmited = rs.getString("dateSubmitted");
                storyList.add( new Story(id,categoryId,img,name1,price,writer,dateSubmited));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storyList;
    }

    @Override
    public Story findStoryById(int id) {
        Story story = new Story();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM STORY WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                int categoryId = rs.getInt("categoryId");
                String img = rs.getString("img");
                String name = rs.getString("name");
                int price = (int) rs.getDouble("price");
                String writer = rs.getString("author");
                String dateSubmited = rs.getString("dateSubmitted");
                story = new Story(id1,categoryId,img,name,price,writer,dateSubmited);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return story;
    }
}
