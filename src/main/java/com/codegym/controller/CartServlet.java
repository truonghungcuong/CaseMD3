package com.codegym.controller;

import com.codegym.dao.cart.CartDAO;
import com.codegym.dao.cart.ICartDAO;
import com.codegym.dao.part.IPartDAO;
import com.codegym.dao.partImage.IPartImageDAO;
import com.codegym.dao.partImage.PartImageDAO;

import com.codegym.dao.story.IStoryDAO;
import com.codegym.dao.part.PartDAO;
import com.codegym.dao.story.StoryDAO;
import com.codegym.dao.user.IUserDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    private IStoryDAO storyDAO = new StoryDAO();
    private IPartDAO partDAO = new PartDAO();
    private IPartImageDAO partImageDAO = new PartImageDAO();
    private IUserDAO userDAO = new UserDAO();
    private ICartDAO cartDAO = new CartDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {


            default: {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                int userId = user.getId();
                List<Cart> cartList = this.cartDAO.selectAllOrderById(userId);
                int totalPayMonney = this.cartDAO.totalPayMonney(userId);
                request.setAttribute("totalPayMoney",totalPayMonney);
                request.setAttribute("cartList", cartList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("story/orderStory.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "find": {
                String name = request.getParameter("name");
                String name1 = "%" + name + "%";
                List<Story> storyList = this.storyDAO.selectByName(name1);
                request.setAttribute("storyList", storyList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("story/list3.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "login": {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean checkLogin = this.userDAO.checkLogin(username, password);
                User user = this.userDAO.findUserByUsernameAndPassword(username, password);

                if (checkLogin) {
                    request.setAttribute("user", user);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    List<Story> storyList = this.storyDAO.selectAllStory();
                    request.setAttribute("storyList", storyList);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("story/list.jsp");
                    dispatcher.forward(request, response);

                }
                break;
            }
            case "register": {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");

                if (confirmPassword.equals(password)) {
                    User newUser = new User(username, password, fullName, email, address, phone);
                    List<Story> storyList = this.storyDAO.selectAllStory();
                    request.setAttribute("storyList", storyList);
                    this.userDAO.register(newUser);
                    request.setAttribute("user", newUser);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("story/list.jsp");
                    dispatcher.forward(request, response);
                } else {

                }
            }

        }
    }
}
