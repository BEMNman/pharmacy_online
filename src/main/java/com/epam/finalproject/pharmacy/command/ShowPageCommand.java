package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;

public class ShowPageCommand implements Command {

    private String page;

    public ShowPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        return CommandResult.redirect(page);
    }
}
