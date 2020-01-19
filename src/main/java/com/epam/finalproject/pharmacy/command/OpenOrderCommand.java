package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OpenOrderCommand implements Command {

    private OrderService service;

    public OpenOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = service.showAllForUser(user);

        System.out.println("orders.size() = " + orders.size());

        request.setAttribute("orders", orders);
        return CommandResult.forward("/pacientMain.jsp");
    }
}
