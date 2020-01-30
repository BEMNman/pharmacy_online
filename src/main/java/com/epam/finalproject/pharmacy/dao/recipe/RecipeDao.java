package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

public interface RecipeDao extends Dao<Recipe> {

    List<Recipe> getAllUsersRecipesForMedicamentForCurrentDate(Long userId, Long medicamentId) throws DaoException;
}
