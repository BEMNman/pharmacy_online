package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.RequestService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code SendRecipeRequestCommand} class is implementation of {@link Command}.
 * This command is used to send new extension <code>request</code>.
 *
 * <p> An object {@code SendRecipeRequestCommand} contains a
 * single field whose type is {@code RequestService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.RequestService
 */

public class SendRecipeRequestCommand implements Command {

    private RequestService service;

    public SendRecipeRequestCommand(RequestService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String recipeId = request.getParameter(RequestParameterConst.RECIPE_ID);
        String requestedPeriod = request.getParameter(RequestParameterConst.REQUESTED_PERIOD);
        service.sendRequest(recipeId, requestedPeriod);
        return CommandResult.redirectToCommand(CommandFactory.OPEN_RECIPES);
    }
}
