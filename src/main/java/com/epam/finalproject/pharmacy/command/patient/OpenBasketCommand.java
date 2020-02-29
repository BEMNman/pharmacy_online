package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The {@code OpenBasketCommand} class is implementation of {@link Command}.
 * This command is used to open basket with medicines.
 *
 * <p> An object {@code OpenBasketCommand} contains a
 * single field whose type is {@code MedicamentService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.MedicamentService
 */

public class OpenBasketCommand implements Command {

    private MedicamentService service;

    public OpenBasketCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        Map<Medicament, Integer> medicinesAndCountInBasket =
                (Map) session.getAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        if (medicinesAndCountInBasket != null) {
            Map<Medicament, Integer> refreshedItemsBasket = service.refreshBasket(medicinesAndCountInBasket);
            session.setAttribute(SessionAttributeConst.MEDICINES_IN_BASKET, refreshedItemsBasket);
        }
        return CommandResult.forward(Page.PATIENT_BASKET);
    }
}
