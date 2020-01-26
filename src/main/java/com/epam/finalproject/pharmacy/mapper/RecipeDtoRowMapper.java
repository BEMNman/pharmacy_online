package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class RecipeDtoRowMapper implements RowMapper<RecipeDto> {
    @Override
    public RecipeDto map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Recipe.COLUMN_ID);
        LocalDate creationDate = resultSet.getDate(Recipe.COLUMN_CREATION_DATE).toLocalDate();
        LocalDate expDate = resultSet.getDate(Recipe.COLUMN_EXP_DATE).toLocalDate();
        String medicamentName = resultSet.getString(Medicament.COLUMN_NAME);
        Integer amount = resultSet.getInt(Recipe.COLUMN_AMOUNT);
        String patientName = resultSet.getString(RecipeDto.COLUMN_PATIENT_NAME);
        String doctorName = resultSet.getString(RecipeDto.COLUMN_DOCTOR_NAME);

        return new RecipeDto(id, creationDate, expDate, medicamentName, amount, patientName, doctorName);
    }

    @Override
    public String getFieldsMapperByStringForQuery() {
        return null;
    }
}
