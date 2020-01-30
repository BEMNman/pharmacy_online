package com.epam.finalproject.pharmacy.command.doctor;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.CreationRecipeService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class OpenCreationRecipeFormCommand implements Command {
    private CreationRecipeService service;

    public OpenCreationRecipeFormCommand(CreationRecipeService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        List<User> patients = service.findAllUserByRole(UserRole.PATIENT);
        List<Medicament> medicines = service.findAllMedicinesWithRecipe();
        request.setAttribute(RequestParameterConst.LIST_MEDICINES, medicines);
        request.setAttribute(RequestParameterConst.LIST_PATIENT, patients);
        LocalDate today = LocalDate.now();
        request.setAttribute(RequestParameterConst.CURRENT_DATE, today);
        return CommandResult.forward(Page.DOCTOR_CREATE_RECIPE);
    }
}
