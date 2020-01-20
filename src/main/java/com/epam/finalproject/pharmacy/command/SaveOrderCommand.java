package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.OrderDetailsService;
import com.epam.finalproject.pharmacy.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveOrderCommand implements Command {

    private OrderService service;
    private OrderDetailsService orderDetailsService;

    public SaveOrderCommand(OrderService service, OrderDetailsService orderDetailsService) {
        this.service = service;
        this.orderDetailsService = orderDetailsService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String[] counts = request.getParameterValues("count");

        System.out.println("count:   " + Arrays.toString(counts));
        String[] ids = request.getParameterValues("id");
        System.out.println("ids:   " + Arrays.toString(ids));

        if(session.getAttribute("medicinesInBasket") == null) {
            return CommandResult.redirect("/error.jsp");
        }
        Map<Medicament, Integer> medicinesInOrder = (LinkedHashMap) session.getAttribute("medicinesInBasket");

        long idNewOrder = service.saveNewOrderForUser(user, medicinesInOrder, counts);
        orderDetailsService.saveOrderDetails(idNewOrder, medicinesInOrder, counts);
        session.removeAttribute("medicinesInBasket");
        session.removeAttribute("listIdItems");
        return CommandResult.redirect("controller?command=authorization");
    }
}
