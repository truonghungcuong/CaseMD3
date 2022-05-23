package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.categoryManagerService.CategoryService;
import com.codegym.service.categoryManagerService.ICategoryService;
import com.codegym.service.storyManagerService.IStoryManagerService;
import com.codegym.service.storyManagerService.StoryManagerService;
import com.codegym.dao.storyManager.StoryManagerDAO;
import com.codegym.dao.category.CategoryDAO;
import com.codegym.model.Category;
import com.codegym.model.Story;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StoryManagerServlet", value = "/stories")
public class StoryManagerServlet extends HttpServlet {
    private IStoryManagerService storyManagerService;
    private ICategoryService categoryService;

    public StoryManagerServlet() {
        this.storyManagerService = new StoryManagerService(new StoryManagerDAO());
        this.categoryService = new CategoryService(new CategoryDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                showDelete(request, response);
                break;
            default:


                showListStory(request, response);
                break;
        }
    }


    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user",user);
        List<Category> categories = categoryService.findAll();
        int id = Integer.parseInt(request.getParameter("id"));
        Story story = storyManagerService.findById(id);
        request.setAttribute("story", story);
        request.setAttribute("categories",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manager/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user",user);
        List<Category> categories = categoryService.findAll();
        int id = Integer.parseInt(request.getParameter("id"));
        Story story = storyManagerService.findById(id);
        request.setAttribute("story", story);
        request.setAttribute("categories",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manager/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user",user);
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories",categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manager/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showListStory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Story> stories = storyManagerService.findAll();
        String q = request.getParameter("q");
        if (q != null){
            stories = storyManagerService.findAllStoryByName(q);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user",user);
        request.setAttribute("stories", stories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manager/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createStory(request, response);
                break;
            case "edit":
                editStory(request, response);
                break;
            case "delete":
                deleteStory(request, response);
                break;
        }
    }

    private void deleteStory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        storyManagerService.deleteById(id);
        response.sendRedirect("stories");
    }

    private void editStory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String img = request.getParameter("img");
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String writer = request.getParameter("author");
        String dateSubmited = request.getParameter("submitted");
        Story story = new Story(categoryId, img, name, (int) price, writer, dateSubmited);
        storyManagerService.updateById(id, story);
        response.sendRedirect("stories");
    }

    private void createStory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String writer = request.getParameter("author");
        String sub = request.getParameter("submitted");
        String img = request.getParameter("img");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Story story = new Story(categoryId,img,name, price,writer,sub);
        storyManagerService.create(story);
        response.sendRedirect("stories");
    }
}
