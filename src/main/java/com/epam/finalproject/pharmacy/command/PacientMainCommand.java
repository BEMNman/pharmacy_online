package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class PacientMainCommand implements Command {

    public static final String REQUEST_LIST_MEDICINES = "medicines";
    public static final String REQUEST_USER = "user";

    private MedicamentService service;

    public PacientMainCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        if (session.getAttribute(REQUEST_USER) == null) {
            return CommandResult.forward("/login.jsp");
        }
        User user = (User) session.getAttribute(REQUEST_USER);
        UserRole userRole = user.getRole();
        if (userRole != UserRole.PACIENT) {
            return CommandResult.redirect("/error.jsp");
        }
        if (session.getAttribute(AddMedicamentInBasketCommand.SESSION_ATTRIBUTE_LIST_ID_ITEMS) == null) {
            session.setAttribute(AddMedicamentInBasketCommand.SESSION_ATTRIBUTE_LIST_ID_ITEMS, new ArrayList<Long>());
        }
        List<Medicament> medicines = service.showAll();
        request.setAttribute(REQUEST_LIST_MEDICINES, medicines);
        return CommandResult.forward("/pacientMain.jsp");
    }
}
