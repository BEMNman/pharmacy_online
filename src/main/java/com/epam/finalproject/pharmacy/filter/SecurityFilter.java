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
        roleAvailableCommand.put(MAIN_PAGE, Arrays.asList(PATIENT, DOCTOR, PHARMACIST));
        roleAvailableCommand.put(OPEN_RECIPES, Arrays.asList(PATIENT, DOCTOR));
        roleAvailableCommand.put(OPEN_ORDER, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(OPEN_ORDERS, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(ADD_MEDICAMENT_IN_BASKET, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(OPEN_BASKET, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(SAVE_ORDER, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(VIEW_ORDER_DETAILS, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(CONTINUE_ORDER, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(CLEAR_BASKET, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(PAY, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(SEND_RECIPE_REQUEST, Collections.singletonList(PATIENT));
        roleAvailableCommand.put(LOGOUT, Arrays.asList(PATIENT, DOCTOR, PHARMACIST));
        roleAvailableCommand.put(DELETE_MEDICAMENT, Collections.singletonList(PHARMACIST));
        roleAvailableCommand.put(EDIT_MEDICAMENT, Collections.singletonList(PHARMACIST));
        roleAvailableCommand.put(SAVE_MEDICAMENT, Collections.singletonList(PHARMACIST));
        roleAvailableCommand.put(OPEN_CREATION_FORM_MEDICAMENT, Collections.singletonList(PHARMACIST));
        roleAvailableCommand.put(OPEN_CREATION_FORM_RECIPE, Collections.singletonList(DOCTOR));
        roleAvailableCommand.put(SAVE_RECIPE, Collections.singletonList(DOCTOR));
        roleAvailableCommand.put(OPEN_REQUESTS, Collections.singletonList(DOCTOR));
        roleAvailableCommand.put(REJECT_REQUEST, Collections.singletonList(DOCTOR));
        roleAvailableCommand.put(APPROVE_REQUEST, Collections.singletonList(DOCTOR));
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
                response.sendError(404, "Not available action!");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
