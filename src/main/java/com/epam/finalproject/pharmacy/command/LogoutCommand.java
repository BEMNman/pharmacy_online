package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("listIdItems");
        return CommandResult.redirect("login.jsp");
    }
}
