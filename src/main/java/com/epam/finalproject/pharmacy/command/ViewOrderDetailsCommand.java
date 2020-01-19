package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewOrderDetailsCommand implements Command {

    public static final String MEDICINES_IN_ORDER = "medicinesInOrder";
    public static final String ORDER_ID = "orderId";

    private final MedicamentService service;

    public ViewOrderDetailsCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String orderIdString = request.getParameter(ORDER_ID);
        long orderId = Long.parseLong(orderIdString);
        List<Medicament> medicinesInOrder = service.findMedicamentForOrder(orderId);
        request.setAttribute(MEDICINES_IN_ORDER, medicinesInOrder);
        return CommandResult.forward("/pacientMain.jsp");
    }
}
