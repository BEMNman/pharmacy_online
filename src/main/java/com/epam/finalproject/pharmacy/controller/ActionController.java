package com.epam.finalproject.pharmacy.controller;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class ActionController extends HttpServlet {

    public void init(ServletConfig config) throws
            ServletException {
        super.init(config);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String page;

        try {
            Command command = CommandFactory.create(request.getParameter("command"));
            page = command.execute(request);

        } catch (Exception e) {
            page = "error.jsp";
        }
        dispatch(request, response, page);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws javax.servlet.ServletException, java.io.IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
