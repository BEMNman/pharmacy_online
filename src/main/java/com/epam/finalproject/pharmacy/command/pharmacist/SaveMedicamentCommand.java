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
        MedicamentForm form = MedicamentForm.valueOf(request.getParameter(RequestParameterConst.MEDICAMENT_FORM).toUpperCase());
        String dosage = request.getParameter(RequestParameterConst.MEDICAMENT_DOSAGE);
        boolean recipe = request.getParameter(RequestParameterConst.MEDICAMENT_RECIPE).equals(TRUE);
        String stringAmountInPack = request.getParameter(RequestParameterConst.MEDICAMENT_AMOUNT_IN_PACK);
        Integer amountInPack = new Integer(stringAmountInPack);
        String medicamentPrice = request.getParameter(RequestParameterConst.MEDICAMENT_PRICE);
        BigDecimal price = new BigDecimal(medicamentPrice);
        String medicamentQuantity = request.getParameter(RequestParameterConst.MEDICAMENT_QUANTITY);
        Integer quantity = new Integer(medicamentQuantity);
        String resultCommand = service.updateMedicament(id, name, form, dosage, recipe, amountInPack, price, quantity);
        request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, resultCommand);
        return CommandResult.redirectToCommand(CommandFactory.MAIN_PAGE);
    }
}
