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

/**
 * The {@code OpenCreationRecipeFormCommand} class is implementation of {@link Command}.
 * This command is used to open form for creating new  <code>recipe</code>.
 *
 * <p> An object {@code OpenCreationRecipeFormCommand} contains a
 * single field whose type is {@code CreationRecipeService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.CreationRecipeService
 */

public class OpenCreationRecipeFormCommand implements Command {
    private CreationRecipeService service;

    public OpenCreationRecipeFormCommand(CreationRecipeService service) {
        this.service = service;
    }

    /**
     * The method returns an object of {@link CommandResult} as a result of executing
     * this command.
     *
     * @param request
     * @return ResultCommand
     * @throws ServerException when the method <code>findAllUserByRole</code>
     *                         or <code>findAllMedicinesWithRecipe</code> throws <code>exception</code>.
     */
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
