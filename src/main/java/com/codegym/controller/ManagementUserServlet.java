package com.codegym.controller;

import com.codegym.dao.usermanagement.UserManagementDAO;
import com.codegym.model.User;

import com.codegym.service.serviceUserManagement.IUserManagementService;
import com.codegym.service.serviceUserManagement.UserManagementService;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;



import java.util.List;

@WebServlet(name = "ManagementUserServlet", value = "/userservlet")
public class ManagementUserServlet extends HttpServlet {
    private IUserManagementService userManagementService;

    public ManagementUserServlet() {
        this.userManagementService = new UserManagementService(new UserManagementDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/managementUser/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "edit": {
                showEditForm(request, response);
                break;
            }
            case "delete": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                request.setAttribute("user",user);
                int id = Integer.parseInt(request.getParameter("id"));
                User user1 = userManagementService.findById(id);
                request.setAttribute("user", user1);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/managementUser/delete.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "view":{
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                request.setAttribute("user",user);
                int id = Integer.parseInt(request.getParameter("id"));
                User user1 = userManagementService.findById(id);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/managementUser/view.jsp");
                dispatcher.forward(request, response);
                break;
            }
            default:
                showListUser(request, response);
                break;
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user",user);
        int id = Integer.parseInt(request.getParameter("id"));
        User user1 = userManagementService.findById(id);
        request.setAttribute("user", user1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/managementUser/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user",user);
        List<User> users = userManagementService.findAll();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/managementUser/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                createUser(request, response);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                userManagementService.deleteById(id);
                response.sendRedirect("/userservlet");
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String fullName = request.getParameter("fullName");
                boolean role = Boolean.parseBoolean(request.getParameter("role"));
                String email = request.getParameter("email");
                String img = request.getParameter("img");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                User user = new User(username, password, fullName, role, email, img, address, phone);
                boolean isUpdate = userManagementService.updateById(id, user);
                String message = "";
                if (isUpdate) {
                    message="1";
                    request.setAttribute("message",message);
                    RequestDispatcher dispatcher=request.getRequestDispatcher("/managementUser/edit.jsp");
                    dispatcher.forward(request,response);
                } else {
                    message="2";
                    request.setAttribute("message",message);
                    RequestDispatcher dispatcher=request.getRequestDispatcher("/managementUser/edit.jsp");
                    dispatcher.forward(request,response);
                }
                break;
            }
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        boolean role = Boolean.parseBoolean(request.getParameter("role"));
        String email = request.getParameter("email");
        String img = request.getParameter("img");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        User user = new User(username, password, fullName, role, email, img, address, phone);
        boolean isCreate=userManagementService.create(user);
        String message="";
        if (isCreate){
            message="1";
            request.setAttribute("message",message);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/managementUser/create.jsp");
            dispatcher.forward(request,response);
        }else {
            message="2";
            request.setAttribute("message",message);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/managementUser/create.jsp");
            dispatcher.forward(request,response);
        }

    }
}