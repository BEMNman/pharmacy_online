package com.epam.finalproject.pharmacy.controller;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.connection.ConnectionPool;
import com.epam.finalproject.pharmacy.exception.DaoException;
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
            int codStatus = 404;
            if (e.getCause() instanceof DaoException) {
                codStatus = 500;
            }
            response.sendError(codStatus, e.getMessage());
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

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeAllConnection();
        super.destroy();
    }
}
