package com.epam.finalproject.pharmacy.command.patient;

import com.epam.finalproject.pharmacy.command.Command;
import com.epam.finalproject.pharmacy.command.CommandResult;
import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.RecipeDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class ShowRecipesCommand implements Command {


    private RecipeDtoService service;

    public ShowRecipesCommand(RecipeDtoService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        List<RecipeDto> recipes = service.findAllRecipesDtoForUser(user);
        request.setAttribute(RequestParameterConst.RECIPES, recipes);
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(RequestParameterConst.CURRENT_DATE, currentDate);
        return CommandResult.forward(Page.PATIENT_RECIPES);
    }
}
