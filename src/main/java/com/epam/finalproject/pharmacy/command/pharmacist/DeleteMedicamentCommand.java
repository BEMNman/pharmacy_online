package com.epam.finalproject.pharmacy.command.pharmacist;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code DeleteMedicamentCommand} class is implementation of {@link Command}.
 * This command is used to delete <code>medicament</code> in data base.
 *
 * <p> An object {@code DeleteMedicamentCommand} contains a
 * single field whose type is {@code MedicamentService}.
 *
 * @author Gogolinsky
 *
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.MedicamentService
 */

public class DeleteMedicamentCommand implements Command {

    private MedicamentService service;

    public DeleteMedicamentCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String medicamentId = request.getParameter(RequestParameterConst.MEDICAMENT_ID);
        Long id = Long.parseLong(medicamentId);
        service.deleteMedicamentById(id);
        return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);
    }
}
