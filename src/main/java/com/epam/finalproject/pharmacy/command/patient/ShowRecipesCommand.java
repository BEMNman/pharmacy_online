package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.RecipeDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class ShowRecipesCommand implements Command {

    private static final String DONT_HAVE_RECIPES = "message.dont_have_recipes";
    private RecipeDtoService service;

    public ShowRecipesCommand(RecipeDtoService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        List<RecipeDto> recipes = service.findAllRecipesDtoForUser(user);
        if(!recipes.isEmpty()) {
            request.setAttribute(RequestParameterConst.RECIPES, recipes);
            LocalDate currentDate = LocalDate.now();
            request.setAttribute(RequestParameterConst.CURRENT_DATE, currentDate);
        } else {
            request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, DONT_HAVE_RECIPES);
        }
        String page = null;
        if (user.getRole() == UserRole.PATIENT) {
            page = Page.PATIENT_RECIPES;
        } else {
            page = Page.DOCTOR_RECIPES;
        }
        return CommandResult.forward(page);
    }
}
