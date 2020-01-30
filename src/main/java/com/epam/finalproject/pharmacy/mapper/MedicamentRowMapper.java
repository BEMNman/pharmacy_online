package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.MedicamentForm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicamentRowMapper implements RowMapper<Medicament> {
    @Override
    public Medicament map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Medicament.COLUMN_ID);
        String name = resultSet.getString(Medicament.COLUMN_NAME);
        String medicamentFormString = resultSet.getString(Medicament.COLUMN_FORM);
        MedicamentForm medicamentForm = MedicamentForm.valueOf(medicamentFormString);
        String dosage = resultSet.getString(Medicament.COLUMN_DOSAGE);
        boolean needRecipe = resultSet.getBoolean(Medicament.COLUMN_RECIPE);
        int amount = resultSet.getInt(Medicament.COLUMN_AMOUNT_IN_PACK);
        BigDecimal price = resultSet.getBigDecimal(Medicament.COLUMN_PRICE);
        int quantityInStock = resultSet.getInt(Medicament.COLUMN_QUANTITY_IN_STOCK);
        boolean archive = resultSet.getBoolean(Medicament.COLUMN_ARCHIVE);
        return new Medicament(id, name, medicamentForm, dosage, needRecipe, amount, price, quantityInStock ,archive);
    }
}
