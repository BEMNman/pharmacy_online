package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.OrderDetailsService;
import com.epam.finalproject.pharmacy.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;
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
        if(session.getAttribute("medicinesInOrder") == null) {
            return CommandResult.redirect("/error.jsp");
        }
        User user = (User)session.getAttribute("user");
        Map<Medicament, Integer> medicinesInOrder = (Hashtable)session.getAttribute("medicinesInOrder");
        long idNewOrder = service.saveNewOrderForUser(user, medicinesInOrder);
        orderDetailsService.saveOrderDetails(idNewOrder, medicinesInOrder);
        session.removeAttribute("medicinesInOrder");
        session.removeAttribute("listIdItems");
        return CommandResult.redirect("controller?command=authorization");
    }
}
