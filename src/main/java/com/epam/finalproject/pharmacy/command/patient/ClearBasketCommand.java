package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ClearBasketCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        return CommandResult.redirectToCommand("patientMain");
    }
}
