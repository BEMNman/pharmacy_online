package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code RegisterNewPatientCommand} class is implementation of {@link Command}.
 * This command is used to register new <code>user</code>.
 *
 * <p> An object {@code RegisterNewPatientCommand} contains a
 * single field whose type is {@code UserService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.UserService
 */

public class RegisterNewPatientCommand implements Command {

    private final UserService service;

    public RegisterNewPatientCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String password = request.getParameter(RequestParameterConst.USER_PASSWORD);
        String passwordForCheck = request.getParameter(RequestParameterConst.NEW_USER_PASSWORD_CHECKED);
        String login = request.getParameter(RequestParameterConst.USER_LOGIN);
        String patientName = request.getParameter(RequestParameterConst.USER_NAME);
        boolean registrationSuccessful =
                service.registerNewUserByPatient(patientName, login, password, passwordForCheck);
        if (registrationSuccessful) {
            return CommandResult.redirect(Page.INDEX);
        } else {
            throw new ServerException("Error in registration.");
        }
    }
}
