package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        session.invalidate();
        return CommandResult.redirect(Page.LOGIN);
    }
}
