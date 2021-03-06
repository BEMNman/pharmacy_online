package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifable> {

    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifable> create(String table) {
        switch (table) {
            case User.NAME_TABLE_IN_DB:
                return new UserRowMapper();
            case Medicament.NAME_TABLE_IN_DB:
                return new MedicamentRowMapper();
            case Order.NAME_TABLE_IN_DB:
                return new OrderRowMapper();
            case OrderDetails.NAME_TABLE_IN_DB:
                return new OrderDetailsRowMapper();
            case Recipe.NAME_TABLE_IN_DB:
                return new RecipeRowMapper();
            case Request.NAME_TABLE_IN_DB:
                return new RequestRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table = " + table);
        }
    }
}
