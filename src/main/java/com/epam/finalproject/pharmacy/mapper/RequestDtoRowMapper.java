package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.dto.RequestDto;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.entity.Request;
import com.epam.finalproject.pharmacy.entity.RequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RequestDtoRowMapper implements RowMapper<RequestDto> {
    @Override
    public RequestDto map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Request.COLUMN_ID);
        LocalDate creationDate = resultSet.getDate(Request.COLUMN_CREATION_DATE).toLocalDate();
        RequestStatus status = RequestStatus.valueOf(resultSet.getString(Request.COLUMN_STATUS));
        String medicamentName = resultSet.getString(RequestDto.COLUMN_MEDICAMENT_NAME);
        String medicamentDosage = resultSet.getString(Medicament.COLUMN_DOSAGE);
        Integer quantity = resultSet.getInt(Recipe.COLUMN_AMOUNT);
        String patientName = resultSet.getString(RequestDto.COLUMN_PATIENT_NAME);
        LocalDate expDate = resultSet.getDate(Recipe.COLUMN_EXP_DATE).toLocalDate();
        Integer requestedPeriod = resultSet.getInt(Request.COLUMN_REQUESTED_PERIOD);
        return new RequestDto(id, creationDate, status, medicamentName,
                medicamentDosage, quantity, patientName, expDate, requestedPeriod);
    }
}
