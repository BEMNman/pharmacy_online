package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class SaveOrderCommand implements Command {

    private OrderService service;

    public SaveOrderCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        Map<Medicament, Integer> medicinesInOrder =
                (Map) session.getAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        long idNewOrder = service.saveNewOrderForUser(user, medicinesInOrder);
        service.saveOrderDetails(idNewOrder, medicinesInOrder);
        session.removeAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);
    }
}
