package com.epam.finalproject.pharmacy.command.doctor;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.ExtensionRecipeService;

import javax.servlet.http.HttpServletRequest;

public class ApproveRequestCommand implements Command {

    private ExtensionRecipeService service;

    public ApproveRequestCommand(ExtensionRecipeService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String stringRequestId = request.getParameter(RequestParameterConst.REQUEST_ID);
        if(stringRequestId == null || stringRequestId.isEmpty()){
            throw new ServerException("Parameter 'requestId' is incorrect");
        }
        Long requestId = Long.parseLong(stringRequestId);
        service.approveRequest(requestId);
        return CommandResult.redirectToCommand(CommandFactory.OPEN_REQUESTS);
    }
}
