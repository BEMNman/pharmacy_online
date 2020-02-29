package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

/**
 * The interface extends an {@link Dao}.
 * The interface contains description the single method for
 * working with the database for objects {@link Recipe}.
 *
 * @author Gogolinsky
 * @see Dao
 * @see Recipe
 */

public interface RecipeDao extends Dao<Recipe> {

    /**
     * The method returns all active user's recipe {@link Recipe}
     * for medicament {@link com.epam.finalproject.pharmacy.entity.Medicament}.
     *
     * @param userId       type is {@link Long}.
     *                     <code>userId</code> is user's id for which we are looking for
     *                     active recipes.
     * @param medicamentId type is {@link Long}.
     *                     <code>medicamentId</code> is id of medicament in searched active recipe.
     * @return {@link List} with objects {@link Recipe} for medicament
     * which belong to the user with <code>userId</code>.
     * If objects {@link Recipe} weren't found, the list will be empty.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<Recipe> getAllUsersRecipesForMedicamentForCurrentDate(Long userId, Long medicamentId) throws DaoException;
}
