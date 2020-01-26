package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.OrderService;
import com.epam.finalproject.pharmacy.command.util.CreditCartValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class PayOrderCommand implements Command {

    private OrderService service;

    public PayOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        Map<Medicament, Integer> medicinesOrder =
                (Map) session.getAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        String cardName = request.getParameter(RequestParameterConst.CARD_NAME);
        String cardNumber = request.getParameter(RequestParameterConst.CARD_NUMBER);
        String expDate  = request.getParameter(RequestParameterConst.CARD_EXP_DATE);
        String cvv  = request.getParameter(RequestParameterConst.CARD_CVV);
        CreditCartValidator creditCartValidator = new CreditCartValidator();
        boolean valid = creditCartValidator.isValid(cardName, cardNumber, expDate, cvv);
        if (valid) {
            Long orderId = service.saveNewOrderForUser(user,medicinesOrder);
            service.saveOrderDetails(orderId, medicinesOrder);
            session.removeAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, "The order placed");
            return CommandResult.forward(Page.PATIENT_BASKET);
        }
        request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP,
                "credit card information entered is not correct");
        return CommandResult.forward(Page.PATIENT_BASKET);
    }
}
