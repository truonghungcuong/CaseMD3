package com.codegym.dao.cart;

import com.codegym.model.Cart;

import java.util.List;

public interface ICartDAO {

        List<Cart> selectAllOrderById(int userId);

        void insertNewOrder(Cart cart);

        boolean deleteOder(int id);

        Cart selectAllOrderById1(int id);

        int totalPayMonney(int userId);

}
