package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class AddMedicamentInBasketCommand implements Command {
//    public static final String MESSAGE_NOT_AVAILABLE_RECIPE = "You don't have recipe for this medicament";
//    public static final String MESSAGE_NOT_ENOUGH_IN_STOCK = "Entered quantity is more than in stock";

    private MedicamentService service;

    public AddMedicamentInBasketCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        Map<Medicament, Integer> medicamentCountMap =
                (Map) session.getAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        if (medicamentCountMap == null) {
            medicamentCountMap = new LinkedHashMap<>();
        }
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        String stringMedicamentId = request.getParameter(RequestParameterConst.MEDICAMENT_ID);
        String stringCount = request.getParameter(RequestParameterConst.COUNT_MEDICAMENT);

        String messageResultCommand = service.checkQuantityInStock(user, stringMedicamentId, stringCount, medicamentCountMap);
        if (messageResultCommand.isEmpty()) {
            session.setAttribute(SessionAttributeConst.MEDICINES_IN_BASKET, medicamentCountMap);
            return CommandResult.redirectToCommand("patientMain");
        }
        request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, messageResultCommand);
        return CommandResult.forward(Page.PATIENT_MAIN);
    }

//    @Override
//    public CommandResult execute(HttpServletRequest request) throws ServerException {
//        HttpSession session = request.getSession();
//        Map<Medicament, Integer> medicamentCount =
//                (Map) session.getAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
//        if (medicamentCount == null) {
//            medicamentCount = new LinkedHashMap<>();
//        }
//        User user = (User) session.getAttribute(SessionAttributeConst.USER);
//        String stringMedicamentId = request.getParameter(RequestParameterConst.MEDICAMENT_ID);
//        Long medicamentId = Long.parseLong(stringMedicamentId);
//
//        Optional<Medicament> optional = service.findMedicamentById(medicamentId);
//        Medicament medicament = null;
//        if (optional.isPresent()) {
//            medicament = optional.get();
//        } else {
//            throw new ServerException("Medicament wasn't found");
//        }
//
//        boolean hasRecipe = service.checkRecipesAvailable(user, medicament);
//        if (!hasRecipe) {
//            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, MESSAGE_NOT_AVAILABLE_RECIPE);
//            return CommandResult.forward("/patientMain.jsp");
//        }
//******
//        Integer count = Integer.parseInt(stringCount);
//        if (medicamentCount.containsKey(medicament)) {
//            Integer countAllMedicinesInBasket = medicamentCount.get(medicament) + count;
//            if (countAllMedicinesInBasket > medicament.getQuantity()) {
//                String message ="Quantity for " + medicament.getName() + "is more than in stock";
//                request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, message);
//                return CommandResult.forward("/patientMain.jsp");
////                throw new ServerException("Count medicines in basket more than in stock");
//            }
//            medicamentCount.put(medicament, countAllMedicinesInBasket);
//        } else {
//            medicamentCount.put(medicament, count);
//        }

//        String stringCount = request.getParameter(RequestParameterConst.COUNT_MEDICAMENT);
//        boolean hasAvailableQuantity = service.checkQuantityAvailable(medicament, stringCount, medicamentCount);
//        if (!hasAvailableQuantity) {
//            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP,
//                    medicament.getName());
////                    medicament.getName() + MESSAGE_NOT_AVAILABLE_QUANTITY);
//            return CommandResult.forward("/patientMain.jsp");
//        }
//        session.setAttribute(SessionAttributeConst.MEDICINES_IN_BASKET, medicamentCount);
//        return CommandResult.redirectToCommand("patientMain");
//    }


}
