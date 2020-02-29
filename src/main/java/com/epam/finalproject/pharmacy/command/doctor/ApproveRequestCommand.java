package com.epam.finalproject.pharmacy.command.doctor;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.ExtensionRecipeService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code ApproveRequestCommand} class is implementation of {@link Command}.
 * This command is used  to confirm a user <code>request</code> for a <code>recipe</code> extension.
 *
 * <p> An object {@code ApproveRequestCommand} contains a
 * single field whose type is {@code ExtensionRecipeService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.ExtensionRecipeService
 */

public class ApproveRequestCommand implements Command {

    private ExtensionRecipeService service;

    public ApproveRequestCommand(ExtensionRecipeService service) {
        this.service = service;
    }

    /**
     * The method returns an object of {@link CommandResult} as a result of executing
     * the {@link com.epam.finalproject.pharmacy.entity.Request} approval command.
     *
     * @param request
     * @return CommandResult
     * @throws ServerException when same input parameter from request is <code>null</code> or empty
     *                         or method <code>approveRequest</code> throws <code>exception</code>.
     */
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String stringRequestId = request.getParameter(RequestParameterConst.REQUEST_ID);
        if (stringRequestId == null || stringRequestId.isEmpty()) {
            throw new ServerException("Parameter 'requestId' is incorrect");
        }
        Long requestId = Long.parseLong(stringRequestId);
        service.approveRequest(requestId);
        return CommandResult.redirectToCommand(CommandFactory.OPEN_REQUESTS);
    }
}
