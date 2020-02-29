package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code ShowOrdersCommand} class is implementation of {@link Command}.
 * This command is used to show <code>orders</code> for user.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 */

public class ShowOrdersCommand implements Command {

    public static final String DONT_HAVE_ORDERS = "message.dont_have_orders";

    private OrderService service;

    public ShowOrdersCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        User user = (User) request.getSession().getAttribute(SessionAttributeConst.USER);
        List<Order> orders = service.showAllForUser(user);
        request.setAttribute(RequestParameterConst.ORDERS, orders);
        if (orders.isEmpty()) {
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, DONT_HAVE_ORDERS);
        }
        return CommandResult.forward(Page.PATIENT_ORDER_DETAILS);
    }
}
