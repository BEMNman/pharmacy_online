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
import com.epam.finalproject.pharmacy.service.OrderService;
import com.epam.finalproject.pharmacy.util.CreditCartValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class PayOrderCommand implements Command {

    public static final String INVALID_CREDIT_CART_DATA = "credit card's information entered is not correct";
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
        boolean valid = CreditCartValidator.isValid(cardName, cardNumber, expDate, cvv);
        if (valid) {
            service.saveNewOrderWithDetailsForUser(user,medicinesOrder);
            session.removeAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
            return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);
        }
        request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, INVALID_CREDIT_CART_DATA);
        return CommandResult.forward(Page.PATIENT_BASKET);
    }
}
