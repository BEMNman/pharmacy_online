package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.dto.RequestDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

/**
 * The interface extends an {@link Dao}.
 * The interface contains description the single method for
 * working with the database for objects {@link RecipeDto}.
 *
 * @author Gogolinsky
 * @see Dao
 * @see RecipeDto
 */

public interface RecipeDtoDao extends Dao<RecipeDto>{

    /**
     * The method finds all recipes {@link com.epam.finalproject.pharmacy.entity.Recipe}
     * in the database for <code>user</code>.
     *
     * @param user type is {@link User}.
     * @return {@link List} with objects {@link RecipeDto} which belong to the <code>user</code>.
     * If objects {@link RecipeDto} weren't found, the list will be empty.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<RecipeDto> getAllRecipesDtoForUser(User user) throws DaoException;
}
