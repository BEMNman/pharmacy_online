package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.entity.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RecipeRowMapper implements RowMapper<Recipe> {

    @Override
    public Recipe map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Recipe.COLUMN_ID);
        LocalDate creationDate = resultSet.getDate(Recipe.COLUMN_CREATION_DATE).toLocalDate();
        LocalDate expDate = resultSet.getDate(Recipe.COLUMN_EXP_DATE).toLocalDate();
        Long medicamentId = resultSet.getLong(Recipe.COLUMN_MEDICAMENT_ID);
        Integer amount = resultSet.getInt(Recipe.COLUMN_AMOUNT);
        Long patientId = resultSet.getLong(Recipe.COLUMN_PATIENT_ID);
        Long doctorId = resultSet.getLong(Recipe.COLUMN_DOCTOR_ID);
        return new Recipe(id, creationDate, expDate, medicamentId, amount, patientId, doctorId);
    }
}
