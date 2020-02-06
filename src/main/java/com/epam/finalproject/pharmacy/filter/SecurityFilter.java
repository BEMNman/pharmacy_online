package com.epam.finalproject.pharmacy.filter;

import com.epam.finalproject.pharmacy.command.CommandFactory;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityFilter implements Filter {

    private final static List<String> listCommandUnknownUser = Arrays.asList(
            CommandFactory.LOGIN,
            CommandFactory.REGISTER_NEW_PATIENT,
            CommandFactory.SAVE_NEW_PATIENT,
            CommandFactory.SHOW_ERROR_PAGE);

    private final static List<String> LIST_COMMAND_PATIENT = Arrays.asList(
            CommandFactory.MAIN_PAGE,
            CommandFactory.OPEN_RECIPES,
            CommandFactory.OPEN_ORDER,
            CommandFactory.OPEN_ORDERS,
            CommandFactory.ADD_MEDICAMENT_IN_BASKET,
            CommandFactory.OPEN_BASKET,
            CommandFactory.SAVE_ORDER,
            CommandFactory.VIEW_ORDER_DETAILS,
            CommandFactory.CONTINUE_ORDER,
            CommandFactory.CLEAR_BASKET,
            CommandFactory.PAY,
            CommandFactory.SEND_RECIPE_REQUEST,
            CommandFactory.LOGOUT,
            CommandFactory.SHOW_ERROR_PAGE);
    private final static List<String> LIST_COMMAND_PHARMACIST = Arrays.asList(
            CommandFactory.MAIN_PAGE,
            CommandFactory.DELETE_MEDICAMENT,
            CommandFactory.EDIT_MEDICAMENT,
            CommandFactory.SAVE_MEDICAMENT,
            CommandFactory.OPEN_CREATION_FORM_MEDICAMENT,
            CommandFactory.LOGOUT,
            CommandFactory.SHOW_ERROR_PAGE);
    private final static List<String> LIST_COMMAND_DOCTOR = Arrays.asList(
            CommandFactory.MAIN_PAGE,
            CommandFactory.OPEN_CREATION_FORM_RECIPE,
            CommandFactory.SAVE_RECIPE,
            CommandFactory.OPEN_REQUESTS,
            CommandFactory.OPEN_RECIPES,
            CommandFactory.REJECT_REQUEST,
            CommandFactory.APPROVE_REQUEST,
            CommandFactory.LOGOUT,
            CommandFactory.SHOW_ERROR_PAGE);
    private final static Map<UserRole, List<String>> roleAvailableCommand = new HashMap<>();

    static {
        roleAvailableCommand.put(UserRole.PATIENT, LIST_COMMAND_PATIENT);
        roleAvailableCommand.put(UserRole.DOCTOR, LIST_COMMAND_DOCTOR);
        roleAvailableCommand.put(UserRole.PHARMACIST, LIST_COMMAND_PHARMACIST);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        String command = request.getParameter(RequestParameterConst.COMMAND);
        if (user == null) {
            if (listCommandUnknownUser.contains(command)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(Page.INDEX);
            }
        } else {
            UserRole userRole = user.getRole();
            boolean availableCommand = roleAvailableCommand.get(userRole).contains(command);
            if (availableCommand) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(CommandResult.redirectToCommand(CommandFactory.SHOW_ERROR_PAGE).getPage());
            }
        }
    }
}
