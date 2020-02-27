package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;
import com.epam.finalproject.pharmacy.util.Calculator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowMainPageCommand implements Command {

    private static final int COUNT = 13;
    private static final int MIN_NUMBER_PAGE = 1;

    private MedicamentService service;

    public ShowMainPageCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        int quantityRows = service.calculateRowsAvailableMedicines();
        String startPage = request.getParameter(RequestParameterConst.PAGE);
        int currentPage = (startPage != null && !startPage.isEmpty()) ?
                Integer.parseInt(startPage) : 1;
        Calculator calculator = new Calculator();
        int startRow = calculator.calculateStartRow(currentPage, COUNT, quantityRows);
        List<Medicament> medicines = service.showMedicinesOnPage(startRow, COUNT);
        request.setAttribute(RequestParameterConst.LIST_MEDICINES, medicines);
        int maxPage = calculator.calculateMaxPage(COUNT, quantityRows);
        int showedPage = currentPage > maxPage ? maxPage : Math.max(currentPage, MIN_NUMBER_PAGE);
        request.setAttribute(RequestParameterConst.PAGE, showedPage);
        request.setAttribute(RequestParameterConst.MAX_PAGE, maxPage);
        User user = (User) request.getSession().getAttribute(SessionAttributeConst.USER);
        UserRole userRole = user.getRole();
        switch (userRole) {
            case PATIENT:
                return CommandResult.forward(Page.PATIENT_MAIN);
            case DOCTOR:
                return CommandResult.forward(Page.DOCTOR_MAIN);
            case PHARMACIST:
                return CommandResult.forward(Page.PHARMACIST_MAIN);
            default:
                throw new ServerException("Unknown role of user");
        }
    }
}
