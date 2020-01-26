package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PatientMainCommand implements Command {


    private MedicamentService service;

    public PatientMainCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        User user = (User) request.getSession().getAttribute("user");
        UserRole userRole = user.getRole();

        List<Medicament> medicines = service.showAll();
        request.setAttribute(RequestParameterConst.LIST_MEDICINES, medicines);

        switch (userRole) {
            case PATIENT:
                return CommandResult.forward(Page.PATIENT_MAIN);
            case DOCTOR:
                return CommandResult.forward(Page.DOCTOR_MAIN);
            case PHARMACIST:
                return CommandResult.forward(Page.PHARMACIST_MAIN);
            default:
                return CommandResult.redirect(Page.ERROR);
        }
    }
}