package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    CommandResult execute(HttpServletRequest request) throws ServerException;
}
