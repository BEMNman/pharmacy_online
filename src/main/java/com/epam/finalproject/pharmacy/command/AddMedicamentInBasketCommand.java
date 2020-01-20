package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.exception.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddMedicamentInBasketCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddMedicamentInBasketCommand.class.getName());

    public static final String SESSION_ATTRIBUTE_LIST_ID_ITEMS = "listIdItems";

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String stringMedicamentId = request.getParameter("medicamentId");
        Long medicamentId = Long.parseLong(stringMedicamentId);
        String stringCount = request.getParameter("count");
        Integer count = Integer.parseInt(stringCount);
        HttpSession session = request.getSession();
        List<Long> listIdItems = (ArrayList<Long>) session.getAttribute(SESSION_ATTRIBUTE_LIST_ID_ITEMS);
        listIdItems.add(medicamentId);
        session.setAttribute(SESSION_ATTRIBUTE_LIST_ID_ITEMS, listIdItems);

        logger.debug("Command 'AddMedicamentCommand' was done");

        return CommandResult.redirect("controller?command=pacientMain");
    }
}
