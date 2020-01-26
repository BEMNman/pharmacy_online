package com.epam.finalproject.pharmacy.command.pharmacist;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;

public class EditMedicamentCommand implements Command {

    private MedicamentService service;

    public EditMedicamentCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String medicamentId = request.getParameter(RequestParameterConst.MEDICAMENT_ID);
        Long id = Long.parseLong(medicamentId);
        service.findMedicamentById(id)
                .ifPresent(medicament -> request.setAttribute(RequestParameterConst.MEDICAMENT, medicament));
        return CommandResult.forward(Page.PHARMACIST_EDIT_CREATE_MEDICAMENT);
    }
}
