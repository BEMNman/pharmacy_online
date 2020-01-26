package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Authorization implements Command {

    private static final Logger logger = LogManager.getLogger(Authorization.class.getName());

    public Authorization() {
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
//        User user = (User) request.getSession().getAttribute("user");
//        UserRole userRole = user.getRole();
//        switch (userRole) {
//            case PATIENT:
//                return CommandResult.redirectToCommand(Page.PATIENT_MAIN);
//            case DOCTOR:
                return CommandResult.redirectToCommand("patientMain");
//            case PHARMACIST:
//                return CommandResult.redirectToCommand(Page.PHARMACIST_MAIN);
//            default:
//                return CommandResult.redirect(Page.ERROR);
//        }
    }
}
