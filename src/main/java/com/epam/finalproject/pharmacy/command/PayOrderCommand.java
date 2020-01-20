package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;
import com.epam.finalproject.pharmacy.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PayOrderCommand implements Command {

    private OrderService orderService;
    private MedicamentService medicamentService;

    public PayOrderCommand(OrderService orderService, MedicamentService medicamentService) {
        this.orderService = orderService;
        this.medicamentService = medicamentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        boolean isCorrect = medicamentService.checkMedicinesInUsersOrder(userId, orderId);
        return null;
    }
}
