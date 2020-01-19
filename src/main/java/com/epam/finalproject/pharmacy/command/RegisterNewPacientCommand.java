package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterNewPacientCommand implements Command {

    private final UserService service;

    public RegisterNewPacientCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String password = request.getParameter("password");
        String passwordForCheck = request.getParameter("passwordForCheck");
        String login =request.getParameter("login");
        if(login == null || login.isEmpty()
                || password == null || password.isEmpty()
                || !password.equals(passwordForCheck)){

            return CommandResult.redirect("error.jsp");
        }
        String pacientName = request.getParameter("name");
        User pacient = User.newPacient(pacientName, login, password);
        service.registerNewUserByPacient(pacient);
        return CommandResult.redirect("index.jsp");

    }
}
