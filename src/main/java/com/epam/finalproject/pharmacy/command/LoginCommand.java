package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginCommand.class.getName());

    private UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            logger.debug("Login or password haven't been entered");

            return CommandResult.redirect("erroe.jsp");
        }
        Optional<User> user = service.login(login, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());
        } else {
            logger.warn("Can't login");

            throw new ServerException();
        }
        logger.debug("Command 'LoginCommand' was redirected to '/controller?command=mainPage'");

        return CommandResult.redirect("controller?command=authorization");

    }
}
