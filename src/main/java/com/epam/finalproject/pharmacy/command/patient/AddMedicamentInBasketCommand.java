package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
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
            return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);
        }
        request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, messageResultCommand);
        return CommandResult.forward(Page.PATIENT_MAIN);
    }
}
