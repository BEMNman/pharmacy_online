package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class OpenBasketCommand implements Command {

    private MedicamentService service;

    public OpenBasketCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        List<Long> listIdItems = (ArrayList<Long>) session.getAttribute("listIdItems");

        Map<Medicament, Integer> medicinesInBasket = service.createMapMedicamentsAmount(listIdItems);

        request.setAttribute("medicinesInBasket", medicinesInBasket);
        return CommandResult.forward("/pacientMain.jsp");
    }
}
