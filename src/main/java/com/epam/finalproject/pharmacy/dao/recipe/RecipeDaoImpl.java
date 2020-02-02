package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.mapper.RecipeRowMapper;
import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RecipeDaoImpl extends AbstractDao<Recipe> implements RecipeDao {

    public static final String GET_ALL_USERS_RECIPES_FOR_MEDICAMENT_CURRENT_DATE =
            "SELECT * FROM recipes WHERE patientId = ? AND medicamentId = ? AND TO_DAYS(expDate) > TO_DAYS(NOW())";

    public RecipeDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected Map<String, Object> getFieldsValues(Recipe item) {
        Map<String, Object> mapFieldsValues = new LinkedHashMap<>();
        mapFieldsValues.put(Recipe.COLUMN_CREATION_DATE, item.getCreatedDate());
        mapFieldsValues.put(Recipe.COLUMN_EXP_DATE, item.getExpDate());
        mapFieldsValues.put(Recipe.COLUMN_MEDICAMENT_ID, item.getMedicamentId());
        mapFieldsValues.put(Recipe.COLUMN_AMOUNT, item.getQuantity());
        mapFieldsValues.put(Recipe.COLUMN_PATIENT_ID, item.getPatientId());
        mapFieldsValues.put(Recipe.COLUMN_DOCTOR_ID, item.getDoctorId());
        return mapFieldsValues;
    }

    @Override
    protected String getTableName() {
        return Recipe.NAME_TABLE_IN_DB;
    }

    @Override
    public List<Recipe> getAllUsersRecipesForMedicamentForCurrentDate(
            Long userId, Long medicamentId) throws DaoException {
        return executeQuery(GET_ALL_USERS_RECIPES_FOR_MEDICAMENT_CURRENT_DATE, new RecipeRowMapper(),
                userId, medicamentId);
    }
}
