package com.epam.finalproject.pharmacy.dao.request;

import com.epam.finalproject.pharmacy.connection.ProxyConnection;
import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.entity.Request;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestDaoImpl extends AbstractDao<Request> implements RequestDao {

    public RequestDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected Map<String, Object> getFieldsValues(Request item) {
        Map<String, Object> mapFieldsValues = new LinkedHashMap<>();
        mapFieldsValues.put(Request.COLUMN_CREATION_DATE, item.getCreationDate());
        mapFieldsValues.put(Request.COLUMN_RECIPE_ID, item.getRecipeId());
        mapFieldsValues.put(Request.COLUMN_REQUESTED_PERIOD, item.getRequestedPeriod());
        mapFieldsValues.put(Request.COLUMN_STATUS, item.getStatus().name());
        return mapFieldsValues;
    }

    @Override
    protected String getTableName() {
        return Request.NAME_TABLE_IN_DB;
    }

}
