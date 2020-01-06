package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.Client;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws ServerException {
        UserService service = new UserService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<Client> user = service.login(login, password);

        if(user.isPresent()) {
            request.getSession(true).setAttribute("user", user.get());
            return  "/index.jsp";
        } else {
            request.setAttribute("error.message", "Incorrect login or password");
        }
        return null;
    }
}
