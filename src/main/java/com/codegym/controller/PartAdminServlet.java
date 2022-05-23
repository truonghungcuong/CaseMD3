package com.codegym.controller;

import com.codegym.dao.partAdmin.IPartAdminDAO;
import com.codegym.dao.partAdmin.PartAdminDAO;
import com.codegym.dao.story.IStoryDAO;
import com.codegym.dao.story.StoryDAO;
import com.codegym.model.Part;
import com.codegym.model.Story;
import com.codegym.model.User;
import com.codegym.service.PartAdmin.IPartAdminService;
import com.codegym.service.PartAdmin.PartAdminService;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PartAdminServlet", value = "/parts")
public class PartAdminServlet extends HttpServlet {
    IPartAdminService partAdminService = new PartAdminService();
    IPartAdminDAO partAdminDAO = new PartAdminDAO();
    IStoryDAO storyDAO = new StoryDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                request.setAttribute("user",user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/partAdmin/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "update": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                request.setAttribute("user",user);
                int id = Integer.parseInt(request.getParameter("id"));
                    Part part = partAdminService.findPartById(id);
                    request.setAttribute("part", part);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("partAdmin/edit.jsp");
                    dispatcher.forward(request, response);
                break;
            }
            case "delete": {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                request.setAttribute("user",user);
                int id = Integer.parseInt(request.getParameter("id"));
                Part part = partAdminService.findPartById(id);
                request.setAttribute("part", part);
                RequestDispatcher dispatcher = request.getRequestDispatcher("partAdmin/delete.jsp");
                dispatcher.forward(request, response);
                break;
            }
           default : {
               HttpSession session = request.getSession();
               User user = (User) session.getAttribute("user");
               request.setAttribute("user",user);
                int storyId1 = Integer.parseInt(request.getParameter("id"));
                Story story = this.storyDAO.findStoryById(storyId1);
                request.setAttribute("story",story);
                List<Part> parts = partAdminService.findAllPart(storyId1);
                request.setAttribute("listPart", parts);
                RequestDispatcher dispatcher = request.getRequestDispatcher("partAdmin/listPart.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                int storyId = Integer.parseInt(request.getParameter("storyId"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                int episode = Integer.parseInt(request.getParameter("episode"));
                String partName = request.getParameter("partName");
                String content = request.getParameter("content");
                Part part = new Part(storyId,categoryId,episode,partName,content);
                this.partAdminDAO.insertNewPart(part);
                List<Part> parts = partAdminService.findAllPart(storyId);
                request.setAttribute("listPart", parts);
                RequestDispatcher dispatcher = request.getRequestDispatcher("partAdmin/listPart.jsp");
                dispatcher.forward(request, response);

                break;
            }
            case "update": {
                int partId = Integer.parseInt(request.getParameter("id"));
                Part part = this.partAdminService.findPartById(partId);
                int storyId = part.getStoryID();
                int categoryId = part.getCategoryId();
                int episode = Integer.parseInt(request.getParameter("episode"));
                String name = request.getParameter("partName");
                String content = request.getParameter("content");
               Part part1 = new Part(storyId,categoryId,episode,name,content);
               this.partAdminService.updatePart(partId,part1);
                List<Part> parts = partAdminService.findAllPart(storyId);
                request.setAttribute("listPart", parts);
                RequestDispatcher dispatcher = request.getRequestDispatcher("partAdmin/listPart.jsp");
                dispatcher.forward(request, response);

            }
            case "delete": {

                int id = Integer.parseInt(request.getParameter("id"));
               this.partAdminDAO.deletePart(id);
               response.sendRedirect("parts");
                break;
            }

        }
    }
}