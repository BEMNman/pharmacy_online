package com.epam.finalproject.pharmacy.command.pharmacist;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.exception.NotAvailableActionException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code SaveMedicamentCommand} class is implementation of {@link Command}.
 * This command is used to save new <code>medicament</code> in data base.
 *
 * <p> An object {@code SaveMedicamentCommand} contains a
 * single field whose type is {@code MedicamentService}.
 *
 * @author Gogolinsky
 *
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.MedicamentService
 */

public class SaveMedicamentCommand implements Command {

    public static final String TRUE = "true";

    private MedicamentService service;

    public SaveMedicamentCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String medicamentId = request.getParameter(RequestParameterConst.MEDICAMENT_ID);
        Long id = medicamentId.isEmpty() ? null : new Long(medicamentId) ;
        String name = request.getParameter(RequestParameterConst.MEDICAMENT_NAME);
        String stringForm = request.getParameter(RequestParameterConst.MEDICAMENT_FORM);
        String dosage = request.getParameter(RequestParameterConst.MEDICAMENT_DOSAGE);
        String stringRecipe = request.getParameter(RequestParameterConst.MEDICAMENT_RECIPE);
        boolean recipe = stringRecipe != null && stringRecipe.equals(TRUE);
        String stringAmountInPack = request.getParameter(RequestParameterConst.MEDICAMENT_AMOUNT_IN_PACK);
        String medicamentPrice = request.getParameter(RequestParameterConst.MEDICAMENT_PRICE);
        String medicamentQuantity = request.getParameter(RequestParameterConst.MEDICAMENT_QUANTITY);
        try {
            service.updateMedicament(id, name, stringForm, dosage, recipe, stringAmountInPack, medicamentPrice, medicamentQuantity);
        } catch (NotAvailableActionException e) {
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, e.getMessage());
            return CommandResult.forward(Page.PHARMACIST_EDIT_CREATE_MEDICAMENT);
        }
        return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);
    }
}
