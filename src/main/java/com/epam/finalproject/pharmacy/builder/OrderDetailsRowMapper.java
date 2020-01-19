package com.epam.finalproject.pharmacy.builder;

import com.epam.finalproject.pharmacy.entity.OrderDetails;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRowMapper implements RowMapper<OrderDetails> {
    @Override
    public OrderDetails map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(OrderDetails.COLUMN_ID);
        long ordertId = resultSet.getLong(OrderDetails.COLUMN_MEDICAMENT_ID);
        long medicamentId = resultSet.getLong(OrderDetails.COLUMN_MEDICAMENT_ID);
        int amount = resultSet.getInt(OrderDetails.COLUMN_QUANTITY);
        BigDecimal totalPrice = resultSet.getBigDecimal(OrderDetails.COLUMN_PRICE);
        return new OrderDetails(id, ordertId, medicamentId, amount, totalPrice);
    }

    @Override
    public String getFieldsMapperByStringForQuery() {
        StringBuilder fieldsString = new StringBuilder();
        fieldsString.append(OrderDetails.COLUMN_ORDER_ID).append(", ")
                .append(OrderDetails.COLUMN_MEDICAMENT_ID).append(", ")
                .append(OrderDetails.COLUMN_QUANTITY).append(", ")
                .append(OrderDetails.COLUMN_PRICE);
        return fieldsString.toString();
    }
}
