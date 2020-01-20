package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.mapper.RecipeDtoRowMapper;

import java.sql.Connection;
import java.util.List;

public class RecipeDtoDaoImpl extends AbstractDao<RecipeDto> implements RecipeDtoDao {

    private static final String GET_ALL_RECIPES_DTO_FOR_USER =
            "SELECT r.id, r.creationDate, r.expDate,  m.name, r.amount, u1.name patientName, u2.name doctorName " +
                    "FROM recipes AS r " +
                    "JOIN medicines AS m ON r.medicamentId=m.id " +
                    "JOIN users AS u1 ON r.patientId=u1.id " +
                    "JOIN users AS u2 ON r.doctorId=u2.id " +
                    "WHERE r.patientId = ?";

    public RecipeDtoDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getValuesItemByStringForQuery(RecipeDto item) {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public List<RecipeDto> getAllRecipesDtoForUser(Long userId) throws DaoException {
        return executeQuery(GET_ALL_RECIPES_DTO_FOR_USER, new RecipeDtoRowMapper(), userId);
    }

    @Override
    public void removeById(Long id) throws DaoException {

    }
}
