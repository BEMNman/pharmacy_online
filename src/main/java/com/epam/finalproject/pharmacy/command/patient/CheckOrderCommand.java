package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.NotAvailableActionException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code CheckOrderCommand} class is implementation of {@link Command}.
 * This command is used to check <code>medicaments</code> in basket before pay./
 *
 * <p> An object {@code CheckOrderCommand} contains a
 * single field whose type is {@code MedicamentService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.MedicamentService
 */

public class CheckOrderCommand implements Command {

    private MedicamentService service;

    public CheckOrderCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        String[] count = request.getParameterValues(RequestParameterConst.COUNT_MEDICAMENT);
        List<Integer> inputQuantityMedicines = Arrays.stream(count)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        Map<Medicament, Integer> medicinesAndCountInBasket =
                (Map) session.getAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        try {
            Map<Medicament, Integer> newMedicinesInBasket =
                    service.recountOrder(user, medicinesAndCountInBasket, inputQuantityMedicines);
            request.getSession().setAttribute(SessionAttributeConst.MEDICINES_IN_BASKET, newMedicinesInBasket);
            return CommandResult.redirectToCommand(CommandFactory.OPEN_ORDER);
        } catch (NotAvailableActionException e) {
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, e.getMessage());
            return CommandResult.forward(Page.PATIENT_ORDER_DETAILS);
        }


    }
}
