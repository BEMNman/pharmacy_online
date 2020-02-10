package com.epam.finalproject.pharmacy.dao.recipe;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.mapper.RecipeDtoRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class RecipeDtoDaoImpl extends AbstractDao<RecipeDto> implements RecipeDtoDao {

    private static final String GET_ALL_RECIPES_DTO_FOR_USER =
            "SELECT r.id, r.creationDate, r.expDate,  m.name, m.dosage, r.amount, u1.name patientName, u2.name doctorName, " +
                    "IF((SELECT count(*) FROM request WHERE recipeId=r.id and status='NEW'), true, false) requested " +
                    "FROM recipes AS r " +
                    "JOIN medicines AS m ON r.medicamentId=m.id " +
                    "JOIN users AS u1 ON r.patientId=u1.id " +
                    "JOIN users AS u2 ON r.doctorId=u2.id ";
    private static final String WHERE_PATIENT_ID = "WHERE r.patientId = ? ORDER BY r.creationDate DESC";
    private static final String WHERE_DOCTOR_ID = "WHERE r.doctorId = ? ORDER BY r.creationDate DESC";

    public RecipeDtoDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected Map<String, Object> getFieldsValues(RecipeDto item) {
        throw  new UnsupportedOperationException("RecipeDto doesn't have table in DB");
    }

    @Override
    protected String getTableName() {
        throw  new UnsupportedOperationException("RecipeDto doesn't have table in DB");
    }

    @Override
    public List<RecipeDto> getAllRecipesDtoForUser(User user) throws DaoException {
        String query = null;
        if(user.getRole()== UserRole.PATIENT) {
            query = GET_ALL_RECIPES_DTO_FOR_USER + WHERE_PATIENT_ID;
        } else {
            query = GET_ALL_RECIPES_DTO_FOR_USER + WHERE_DOCTOR_ID;
        }
        return executeQuery(query, new RecipeDtoRowMapper(), user.getId());
    }
}
