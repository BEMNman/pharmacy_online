package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
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
    public static final String INCORRECT_LOGIN = "Login or password is incorrect";

    private UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String login = request.getParameter(RequestParameterConst.USER_LOGIN);
        String password = request.getParameter(RequestParameterConst.USER_PASSWORD);
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            logger.debug("Login or password haven't been entered");
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, INCORRECT_LOGIN);
            return CommandResult.forward(Page.LOGIN);
        }
        Optional<User> user = service.login(login, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionAttributeConst.USER, user.get());
        } else {
            logger.warn("Can't login");
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, "Login or password is incorrect");
            return CommandResult.forward(Page.LOGIN);
        }
        logger.debug("Command 'LoginCommand' was redirected to '/controller?command=mainPage'");

        return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);

    }
}
