package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.RecipeDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowRecipesCommand implements Command {
    private static final String RECIPES = "recipes";

    private RecipeDtoService service;

    public ShowRecipesCommand(RecipeDtoService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<RecipeDto> recipes = service.findAllRecipesDtoForUser(user);
        request.setAttribute(RECIPES, recipes);
        return CommandResult.forward("/pacientMain.jsp");
    }
}
