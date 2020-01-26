package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewOrderDetailsCommand implements Command {

    private final MedicamentService service;

    public ViewOrderDetailsCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String orderIdString = request.getParameter(RequestParameterConst.ORDER_ID);
        long orderId = Long.parseLong(orderIdString);
        List<Medicament> medicinesInOrder = service.findMedicamentForOrder(orderId);
        request.setAttribute(RequestParameterConst.MEDICINES_IN_ORDER, medicinesInOrder);
        return CommandResult.forward(Page.PATIENT_ORDER_DETAILS);
    }
}
