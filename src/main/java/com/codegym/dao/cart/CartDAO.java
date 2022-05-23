package com.codegym.dao.cart;

import com.codegym.dao.DBConnection;
import com.codegym.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO implements ICartDAO{
    public static final String INSERT_CART_BY_USERID = "INSERT INTO cart(storyId,partId,userId,storyName,storyPart,img,quantity,price,paymoney) VALUES(?,?,?,?,?,?,?,?,?)";
    Connection connection = DBConnection.getConnection();
    @Override
    public List<Cart> selectAllOrderById(int userId) {
        List<Cart> cartList =  new ArrayList();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CART WHERE USERID = ?");
            statement.setInt(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int storyId = rs.getInt("storyId");
                int partId = rs.getInt("partId");
                int userId1 = rs.getInt("userId");
                String storyName = rs.getString("storyName");
                String storyPart = rs.getString("storyPart");
                String img = rs.getString("img");
                int quanlity = rs.getInt("quantity");
                int price = rs.getInt("price");
                int payMoney = rs.getInt("payMoney");


                cartList.add(new Cart(id,storyId,partId,userId1,storyName,storyPart,img,quanlity,price,payMoney));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }
    @Override
    public Cart selectAllOrderById1(int id) {
        Cart cart = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CART WHERE id = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                int storyId = rs.getInt("storyId");
                int partId = rs.getInt("partId");
                int userId = rs.getInt("userId");
                String storyName = rs.getString("storyName");
                String storyPart = rs.getString("storyPart");
                String img = rs.getString("img");
                int quanlity = rs.getInt("quantity");
                int price = rs.getInt("price");
                int payMoney = rs.getInt("payMoney");

                cart = new Cart(id1,storyId,partId,userId,storyName,storyPart,img,quanlity,price,payMoney);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public int totalPayMonney(int userId) {
        List<Cart> cartList = selectAllOrderById(userId);
        int totalPayMonney = 0;
        for (int i = 0; i < cartList.size(); i++) {
            totalPayMonney = totalPayMonney+ cartList.get(i).getPayMoney();
        }
        return totalPayMonney;
    }

    @Override
    public void insertNewOrder(Cart cart) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_CART_BY_USERID);
            statement.setInt(1, cart.getStoryId());
            statement.setInt(2, cart.getPartId());
            statement.setInt(3, cart.getUserId());
            statement.setString(4, cart.getStoryName());
            statement.setString(5, cart.getStoryPart());
            statement.setString(6, cart.getImg());
            statement.setInt(7, cart.getQuantity());
            statement.setInt(8, cart.getPrice());
            statement.setInt(9,cart.getPayMoney());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteOder(int id) {
        boolean rowDelete = false;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM CART WHERE id = ?");
            statement.setInt(1,id);

            rowDelete = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowDelete;
    }
}
