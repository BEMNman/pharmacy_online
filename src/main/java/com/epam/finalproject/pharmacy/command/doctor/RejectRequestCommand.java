package com.epam.finalproject.pharmacy.command.doctor;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.RequestService;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code RejectRequestCommand} class is implementation of {@link Command}.
 * This command is used to reject user's <code>request</code> extension.
 *
 * <p> An object {@code RejectRequestCommand} contains a
 * single field whose type is {@code RequestService}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.RequestService
 */

public class RejectRequestCommand implements Command {

    private RequestService service;

    public RejectRequestCommand(RequestService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String stringRequestId = request.getParameter(RequestParameterConst.REQUEST_ID);
        if (stringRequestId == null || stringRequestId.isEmpty()) {
            throw new ServerException("Parameter 'requestId' is incorrect");
        }
        Long requestId = Long.parseLong(stringRequestId);
        service.rejectRequest(requestId);
        return CommandResult.redirectToCommand(CommandFactory.OPEN_REQUESTS);
    }
}
