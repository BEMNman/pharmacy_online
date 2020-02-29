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

/**
 * The {@code SaveOrderCommand} class is implementation of {@link Command}.
 * This command is used to save new <code>order</code> in data base.
 *
 * <p> An object {@code SaveOrderCommand} contains a
 * single field whose type is {@code OrderService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.OrderService
 */

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
        service.saveNewOrderWithDetailsForUser(user, medicinesInOrder);
        session.removeAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);
    }
}
