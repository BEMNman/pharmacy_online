package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

public interface RecipeDtoDao {
    List<RecipeDto> getAllRecipesDtoForUser(User user) throws DaoException;
}
