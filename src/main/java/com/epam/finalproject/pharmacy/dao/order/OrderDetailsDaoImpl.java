package com.epam.finalproject.pharmacy.dao.order;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.entity.OrderDetails;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderDetailsDaoImpl extends AbstractDao<OrderDetails> implements OrderDetailsDao {

    public OrderDetailsDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected Map<String, Object> getFieldsValues(OrderDetails item) {
        Map<String, Object> mapFieldsValues = new LinkedHashMap<>();
        mapFieldsValues.put(OrderDetails.COLUMN_ORDER_ID, item.getOrderId());
        mapFieldsValues.put(OrderDetails.COLUMN_MEDICAMENT_ID, item.getMedicamentId());
        mapFieldsValues.put(OrderDetails.COLUMN_QUANTITY, item.getQuantity());
        mapFieldsValues.put(OrderDetails.COLUMN_PRICE, item.getPrice());
        return mapFieldsValues;
    }

    @Override
    protected String getTableName() {
        return OrderDetails.NAME_TABLE_IN_DB;
    }

}
