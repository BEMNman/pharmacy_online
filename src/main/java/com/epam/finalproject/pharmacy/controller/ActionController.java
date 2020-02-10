package com.epam.finalproject.pharmacy.controller;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ActionController.class.getName());
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String REQUEST_URL = "requestURL";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws java.io.IOException, ServletException {

        String page;
        try {
            Command command = CommandFactory.create(request.getParameter(RequestParameterConst.COMMAND));
            CommandResult commandResult = command.execute(request);
            page = commandResult.getPage();

            if (commandResult.isRedirect()) {
                redirect(response, page);
            } else {
                forward(request, response, page);
            }
        } catch (Exception e) {
            logger.error(e);
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            request.setAttribute(REQUEST_URL, request.getRequestURL());
            page = Page.ERROR;
            forward (request, response, page);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String page)
            throws javax.servlet.ServletException, java.io.IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        logger.debug("Forward to page: " + page);
        dispatcher.forward(request, response);
    }

    protected void redirect( HttpServletResponse response, String page) throws IOException {
        logger.debug("Redirect to page: " + page);
        response.sendRedirect(page);
    }
}
