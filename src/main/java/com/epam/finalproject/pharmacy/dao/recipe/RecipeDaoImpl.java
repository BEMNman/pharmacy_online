package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.mapper.RecipeRowMapper;
import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.Connection;
import java.util.List;

public class RecipeDaoImpl extends AbstractDao<Recipe> implements RecipeDao {

    public static final String GET_ALL_RECIPES_FOR_USER = "SELECT * FROM recipes WHERE patientId = ?";
    public static final String GET_ALL_USERS_RECIPES_FOR_MEDICAMENT_CURRENT_DATE =
            "SELECT * FROM recipes WHERE patientId = ? AND medicamentId = ? AND TO_DAYS(expDate) > TO_DAYS(NOW())";

    public RecipeDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getValuesItemByStringForQuery(Recipe item) {
        return null;
    }

    @Override
    protected String getTableName() {
        return Recipe.NAME_TABLE_IN_DB;
    }

    @Override
    public List<Recipe> getAllRecipesForUser(Long userId) throws DaoException {
        return executeQuery(GET_ALL_RECIPES_FOR_USER, new RecipeRowMapper(), userId);
    }

    @Override
    public List<Recipe> getAllUsersRecipesForMedicamentForCurrentDate(
            Long userId, Long medicamentId) throws DaoException {
        return executeQuery(GET_ALL_USERS_RECIPES_FOR_MEDICAMENT_CURRENT_DATE, new RecipeRowMapper(),
                userId, medicamentId);
    }
}
