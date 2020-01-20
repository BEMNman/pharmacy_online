package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface RecipeDao extends Dao<Recipe> {
    List<Recipe> getAllRecipesForUser(Long userId) throws DaoException;

    List<Recipe> getAllUsersRecipesForMedicamentForCurrentDate(Long userId, Long medicamentId) throws DaoException;
}
