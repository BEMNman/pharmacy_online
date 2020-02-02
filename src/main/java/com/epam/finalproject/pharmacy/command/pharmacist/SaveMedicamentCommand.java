package com.epam.finalproject.pharmacy.command.pharmacist;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.entity.MedicamentForm;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.MedicamentService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

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
        String resultCommand =
                service.updateMedicament(id, name, stringForm, dosage, recipe, stringAmountInPack, medicamentPrice, medicamentQuantity);
        request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, resultCommand);
        return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);
    }
}
