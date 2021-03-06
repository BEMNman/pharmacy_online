package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.util.Calculator;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

/**
 * The {@code OpenOrderCommand} class is implementation of {@link Command}.
 * This command is used to open new order.
 *
 * <p> An object {@code OpenOrderCommand} doesn't contain any fields.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 */


public class OpenOrderCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        Map<Medicament, Integer> medicinesCountInBasket =
                (Map) session.getAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        Calculator calculator = new Calculator();
        BigDecimal totalPrice = calculator.calculateTotalPrice(medicinesCountInBasket);
        request.setAttribute(RequestParameterConst.TOTAL_PRICE, totalPrice);
        return CommandResult.forward(Page.PATIENT_ORDER_DETAILS);
    }
}
