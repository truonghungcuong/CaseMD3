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


    @WebServlet(name = "CategoryServlet", urlPatterns = "/categories")
    public class CategoryManagerServlet extends HttpServlet {
        private ICategoryService categoryService;
        private IStoryManagerService storyManagerService;

        public CategoryManagerServlet() {
            this.categoryService = new CategoryService(new CategoryDAO());
            this.storyManagerService = new StoryManagerService(new StoryManagerDAO());
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    showCreateCategory(request, response);
                    break;
                case "edit":
                    showEditCategory(request, response);
                    break;
                case "delete":
                    showDeleteCategory(request, response);
                    break;
                case "view":
                    showListStoryByCategory(request, response);
                    break;
                default:
                    showListCategory(request, response);
                    break;
            }
        }

        private void showDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("user",user);
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = categoryService.findById(id);
            request.setAttribute("category",category);
            RequestDispatcher dispatcher = request.getRequestDispatcher("category/delete.jsp");
            dispatcher.forward(request, response);
        }

        private void showEditCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("user",user);
            int id = Integer.parseInt(request.getParameter("id"));
            categoryService.findById(id);
            Category category = categoryService.findById(id);
            request.setAttribute("category",category);
            RequestDispatcher dispatcher = request.getRequestDispatcher("category/edit.jsp");
            dispatcher.forward(request, response);
        }

        private void showCreateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("user",user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("category/create.jsp");
            dispatcher.forward(request, response);
        }

        private void showListStoryByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("user",user);
            int categoryId = Integer.parseInt(request.getParameter("id"));
            List<Story> stories = storyManagerService.findAllProductByCategoryId(categoryId);
            request.setAttribute("stories", stories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("category/view.jsp");
            dispatcher.forward(request, response);
        }

        private void showListCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            request.setAttribute("user",user);
            List<Category> categories = categoryService.findAll();
            String q = request.getParameter("q");
            if (q != null){
                categories = categoryService.findAllCategoryByName(q);
            }
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("category/list.jsp");
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
                    createCategory(request, response);
                    break;
                case "edit":
                    editCategory(request, response);
                case "delete":
                    deleteCategory(request, response);
                default:
                    break;
            }
        }

        private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            categoryService.deleteById(id);
            response.sendRedirect("categories");
        }

        private void editCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Category category = new Category(id,name);
            categoryService.updateById(id,category);
            response.sendRedirect("categories");
        }

        private void createCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String name = request.getParameter("name");
            Category category = new Category();
            categoryService.create(category);
            response.sendRedirect("categories");
        }
    }

