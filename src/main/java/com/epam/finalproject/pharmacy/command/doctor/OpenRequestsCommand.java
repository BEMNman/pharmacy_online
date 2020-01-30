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

public class OpenRequestsCommand implements Command {

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
        return CommandResult.forward(Page.DOCTOR_REQUESTS);
    }
}
