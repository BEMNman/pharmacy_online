package com.epam.finalproject.pharmacy.dao.request;

import com.epam.finalproject.pharmacy.connection.ProxyConnection;
import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.dto.RequestDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.mapper.RequestDtoRowMapper;

import java.util.List;
import java.util.Map;

public class RequestDtoDaoImpl extends AbstractDao<RequestDto> implements RequestDtoDao {

    private static final String GET_ALL_REQUEST_DTO_FOR_DOCTOR =
            "SELECT req.id ,req.creationDate, req.status, m.name AS medicamentName, m.dosage, rec.amount, u.name AS patientName," +
                    " rec.expDate, req.requestedPeriod " +
                    "FROM request AS req " +
                    "JOIN recipes AS rec ON req.recipeId = rec.id " +
                    "JOIN medicines AS m On rec.medicamentId = m.id " +
                    "JOIN users AS u ON u.id = rec.patientId " +
                    "WHERE rec.doctorId = ?";

    public RequestDtoDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<RequestDto> findAllRequestsDtoForDoctor(User user) throws DaoException {

        return executeQuery(GET_ALL_REQUEST_DTO_FOR_DOCTOR, new RequestDtoRowMapper(), user.getId());
    }

    @Override
    protected Map<String, Object> getFieldsValues(RequestDto item) {
        throw new UnsupportedOperationException("RequestDto doesn't have table in DB");
    }

    @Override
    protected String getTableName() {
        throw new UnsupportedOperationException("RequestDto doesn't have table in DB");
    }
}
