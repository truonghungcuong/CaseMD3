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
import com.codegym.model.Part;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StoryServlet", value = "/StoryServlet")
public class StoryServlet extends HttpServlet {
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
            case "register": {
                RequestDispatcher dispatcher = request.getRequestDispatcher("story/register.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "login": {
                RequestDispatcher dispatcher = request.getRequestDispatcher("story/login.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "logOut": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                session.removeAttribute("user");
                List<Story> storyList = this.storyDAO.selectAllStory();
                request.setAttribute("storyList", storyList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("story/list.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "view": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                request.setAttribute("user", user);
                int id = Integer.parseInt(request.getParameter("id"));
                List<Part> parts = this.partDAO.seleceAllPartOfStory(id);
                request.setAttribute("parts", parts);
                RequestDispatcher dispatcher = request.getRequestDispatcher("story/viewPart.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "viewByCategory": {
                int id = Integer.parseInt(request.getParameter("id"));
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (id == 1) {
                    List<Story> storyList = this.storyDAO.selectByCategoryId(id);
                    request.setAttribute("storyList", storyList);
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("story/list1.jsp");
                    dispatcher.forward(request, response);
                } else if (id == 2) {
                    List<Story> storyList = this.storyDAO.selectByCategoryId(id);
                    request.setAttribute("storyList", storyList);
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("story/list2.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            }

            case "read": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    request.setAttribute("message", "Bạn cần đăng nhập mới có thể thao thác!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("story/alert.jsp");
                    dispatcher.forward(request, response);
                } else {
                    int storyId = Integer.parseInt(request.getParameter("storyId"));
                    int partId = Integer.parseInt(request.getParameter("id"));
                    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                    if (categoryId == 1) {
                        List<PartImage> images = this.partImageDAO.selectAllImg(storyId, partId);
                        List<Part> parts = this.partDAO.seleceAllPartOfStory(storyId);
                        request.setAttribute("parts", parts);
                        Part part = this.partDAO.selectById(partId);
                        request.setAttribute("user", user);
                        request.setAttribute("part", part);
                        request.setAttribute("images", images);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("story/viewContent.jsp");
                        dispatcher.forward(request, response);

                    }
                    if (categoryId == 2) {
                        Part part = this.partDAO.selectById(partId);
                        List<Part> parts = this.partDAO.seleceAllPartOfStory(storyId);
                        request.setAttribute("user", user);
                        request.setAttribute("part", part);
                        request.setAttribute("parts", parts);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("story/viewContent1.jsp");
                        dispatcher.forward(request, response);
                    }
                }

                break;
            }
            case "buy": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user == null) {
                    request.setAttribute("message", "Bạn cần đăng nhập mới có thể thao thác!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("story/alert.jsp");
                    dispatcher.forward(request, response);
                } else {
                    int storyId = Integer.parseInt(request.getParameter("storyId"));
                    int partId = Integer.parseInt(request.getParameter("partId"));
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    int quanlity = 1;
                    Story story = this.storyDAO.findStoryById(storyId);
                    String storyName = story.getName();
                    Part part = this.partDAO.selectById(partId);
                    String storyPart = part.getName();
                    String img = story.getImg();
                    int price = (int) story.getPrice();
                    int payMoney = price * quanlity;
                    Cart cart = new Cart(storyId, partId, userId, storyName, storyPart, img, quanlity, price, payMoney);
                    this.cartDAO.insertNewOrder(cart);
                    List<Cart> cartList = this.cartDAO.selectAllOrderById(userId);
                    int totalPayMonney = this.cartDAO.totalPayMonney(userId);
                    request.setAttribute("totalPayMoney",totalPayMonney);
                    request.setAttribute("cartList", cartList);
                    request.setAttribute("part", part);
                    request.setAttribute("story", story);
                    request.setAttribute("user", user);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("story/orderStory.jsp");
                    dispatcher.forward(request, response);
                }


                break;
            }
            case "deleteCart": {
                int id = Integer.parseInt(request.getParameter("id"));
                this.cartDAO.deleteOder(id);
               response.sendRedirect("/CartServlet");
                break;
            }
            case "updateCart" : {
                int id = Integer.parseInt(request.getParameter("id"));
                Cart cart = this.cartDAO.selectAllOrderById1(id);
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                break;
            }
            default: {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                List<Story> storyList = this.storyDAO.selectAllStory();
                request.setAttribute("storyList", storyList);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("story/list.jsp");
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
            case "editCart" : {
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                break;
            }
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
                    if (user.isRole()){
                       response.sendRedirect("/stories");
                    }else {
                        response.sendRedirect("/StoryServlet");
                    }


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
