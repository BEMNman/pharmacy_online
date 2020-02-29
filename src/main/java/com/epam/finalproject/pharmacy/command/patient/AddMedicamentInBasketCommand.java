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
import java.util.*;

/**
 * The {@code AddMedicamentInBasketCommand} class is implementation of {@link Command}.
 * This command is used to add new <code>medicament</code> in basket./
 *
 * <p> An object {@code AddMedicamentInBasketCommand} contains a
 * single field whose type is {@code MedicamentService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.MedicamentService
 */

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
        try {
            service.addMedicamentInBasket(user, stringMedicamentId, stringCount, medicamentCountMap);
        } catch (NotAvailableActionException e) {
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, e.getMessage());
            return CommandResult.forward(Page.PATIENT_MAIN);
        }
        int page = Integer.parseInt(request.getParameter(RequestParameterConst.PAGE));
        session.setAttribute(SessionAttributeConst.MEDICINES_IN_BASKET, medicamentCountMap);
        return CommandResult.redirectToCommandWithParam(CommandFactory.MAIN_PAGE, RequestParameterConst.PAGE, page);
    }
}
