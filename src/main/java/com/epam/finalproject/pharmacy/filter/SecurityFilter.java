package com.epam.finalproject.pharmacy.filter;

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
import java.util.*;

import static com.epam.finalproject.pharmacy.command.CommandFactory.*;
import static com.epam.finalproject.pharmacy.entity.UserRole.*;

public class SecurityFilter implements Filter {

    private final static List<String> availableCommandForUnknownUser = Arrays.asList(
            LOGIN,
            REGISTER_NEW_PATIENT,
            SAVE_NEW_PATIENT,
            SWITCH_LOCALE
    );

    private final static Map<String, List<UserRole>> roleAvailableCommand = new HashMap<>();

    static {
        roleAvailableCommand.put(SHOW_ERROR_PAGE, Arrays.asList(PATIENT, DOCTOR, PHARMACIST));
        roleAvailableCommand.put(MAIN_PAGE, Arrays.asList(PATIENT, DOCTOR, PHARMACIST));
        roleAvailableCommand.put(OPEN_RECIPES, Arrays.asList(PATIENT, DOCTOR));
        roleAvailableCommand.put(OPEN_ORDER, Arrays.asList(PATIENT));
        roleAvailableCommand.put(OPEN_ORDERS, Arrays.asList(PATIENT));
        roleAvailableCommand.put(ADD_MEDICAMENT_IN_BASKET, Arrays.asList(PATIENT));
        roleAvailableCommand.put(OPEN_BASKET, Arrays.asList(PATIENT));
        roleAvailableCommand.put(SAVE_ORDER, Arrays.asList(PATIENT));
        roleAvailableCommand.put(VIEW_ORDER_DETAILS, Arrays.asList(PATIENT));
        roleAvailableCommand.put(CONTINUE_ORDER, Arrays.asList(PATIENT));
        roleAvailableCommand.put(CLEAR_BASKET, Arrays.asList(PATIENT));
        roleAvailableCommand.put(PAY, Arrays.asList(PATIENT));
        roleAvailableCommand.put(SEND_RECIPE_REQUEST, Arrays.asList(PATIENT));
        roleAvailableCommand.put(LOGOUT, Arrays.asList(PATIENT, DOCTOR, PHARMACIST));
        roleAvailableCommand.put(DELETE_MEDICAMENT, Arrays.asList(PHARMACIST));
        roleAvailableCommand.put(EDIT_MEDICAMENT, Arrays.asList(PHARMACIST));
        roleAvailableCommand.put(SAVE_MEDICAMENT, Arrays.asList(PHARMACIST));
        roleAvailableCommand.put(OPEN_CREATION_FORM_MEDICAMENT, Arrays.asList(PHARMACIST));
        roleAvailableCommand.put(OPEN_CREATION_FORM_RECIPE, Arrays.asList(DOCTOR));
        roleAvailableCommand.put(SAVE_RECIPE, Arrays.asList(DOCTOR));
        roleAvailableCommand.put(OPEN_REQUESTS, Arrays.asList(DOCTOR));
        roleAvailableCommand.put(REJECT_REQUEST, Arrays.asList(DOCTOR));
        roleAvailableCommand.put(APPROVE_REQUEST, Arrays.asList(DOCTOR));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        String command = request.getParameter(RequestParameterConst.COMMAND);
        if(user == null) {
            if (availableCommandForUnknownUser.contains(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect(Page.INDEX);
            }
            return;
        }

        if(roleAvailableCommand.containsKey(command)) {
            if(!roleAvailableCommand.get(command).contains(user.getRole())) {
                response.sendRedirect(CommandResult.redirectToCommand(SHOW_ERROR_PAGE).getPage());
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
