package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.entity.Request;
import com.epam.finalproject.pharmacy.entity.RequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RequestRowMapper implements RowMapper<Request> {
    @Override
    public Request map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Request.COLUMN_ID);
        LocalDate creationDate = resultSet.getDate(Request.COLUMN_CREATION_DATE).toLocalDate();
        Long recipeId = resultSet.getLong(Request.COLUMN_RECIPE_ID);
        Integer requestedPeriod = resultSet.getInt(Request.COLUMN_REQUESTED_PERIOD);
        RequestStatus status = RequestStatus.valueOf(resultSet.getString(Request.COLUMN_STATUS));
        return new Request(id, creationDate, recipeId, requestedPeriod,status);
    }
}
