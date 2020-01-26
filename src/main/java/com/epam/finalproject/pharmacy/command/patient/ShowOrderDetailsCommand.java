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

public class ShowOrderDetailsCommand implements Command {

    private OrderService service;

    public ShowOrderDetailsCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        User user = (User) request.getSession().getAttribute(SessionAttributeConst.USER);
        List<Order> orders = service.showAllForUser(user);
        request.setAttribute(RequestParameterConst.ORDERS, orders);
        return CommandResult.forward(Page.PATIENT_ORDER_DETAILS);
    }
}
