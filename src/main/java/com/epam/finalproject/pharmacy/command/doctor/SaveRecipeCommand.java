package com.epam.finalproject.pharmacy.command.doctor;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.RecipeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * The {@code SaveRecipeCommand} class is implementation of {@link Command}.
 * This command is used to save new<code>recipe</code>.
 *
 * <p> An object {@code SaveRecipeCommand} contains a
 * single field whose type is {@code RecipeService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.RecipeService
 */

public class SaveRecipeCommand implements Command {
    private RecipeService service;

    public SaveRecipeCommand(RecipeService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        String stringMedicamentId = request.getParameter(RequestParameterConst.MEDICAMENT_ID);
        String stringPatientId = request.getParameter(RequestParameterConst.PATIENT_ID);
        String stringQuantity = request.getParameter(RequestParameterConst.MEDICAMENT_QUANTITY);
        String stringExpDate = request.getParameter(RequestParameterConst.EXP_DATE);

        service.saveNewRecipe(user, stringMedicamentId, stringPatientId, stringQuantity, stringExpDate);
        return CommandResult.redirectToCommand(CommandFactory.OPEN_RECIPES);
    }
}
