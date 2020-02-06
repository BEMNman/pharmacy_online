package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;

public class OpenBasketCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        return CommandResult.forward(Page.PATIENT_BASKET);
    }
}
