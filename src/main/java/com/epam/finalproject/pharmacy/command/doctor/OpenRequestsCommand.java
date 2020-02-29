package com.epam.finalproject.pharmacy.command.doctor;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.dto.RequestDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.RequestDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code OpenRequestsCommand} class is implementation of {@link Command}.
 * This command is used to open new <code>request</code> extension.
 *
 * <p> An object {@code OpenRequestsCommand} contains a
 * single field whose type is {@code RequestDtoService}.
 *
 * @author Gogolinsky
 *
 * @see com.epam.finalproject.pharmacy.command.Command
 * @see com.epam.finalproject.pharmacy.entity.Request
 * @see com.epam.finalproject.pharmacy.service.RequestDtoService
 */

public class OpenRequestsCommand implements Command {

    public static final String DONT_HAVE_REQUESTS = "message.dont_have_requests";

    private RequestDtoService service;

    public OpenRequestsCommand(RequestDtoService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        List<RequestDto> requestsDto = service.findAllRequestsDtoForDoctor(user);
        request.setAttribute(RequestParameterConst.REQUESTS_FOR_DOCTOR, requestsDto);
        if(requestsDto.isEmpty()) {
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, DONT_HAVE_REQUESTS);
        }
        return CommandResult.forward(Page.DOCTOR_REQUESTS);
    }
}
