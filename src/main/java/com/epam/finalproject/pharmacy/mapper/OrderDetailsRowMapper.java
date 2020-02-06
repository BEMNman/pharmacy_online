package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.entity.OrderDetails;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRowMapper implements RowMapper<OrderDetails> {
    @Override
    public OrderDetails map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(OrderDetails.COLUMN_ID);
        long orderId = resultSet.getLong(OrderDetails.COLUMN_MEDICAMENT_ID);
        long medicamentId = resultSet.getLong(OrderDetails.COLUMN_MEDICAMENT_ID);
        int amount = resultSet.getInt(OrderDetails.COLUMN_QUANTITY);
        BigDecimal totalPrice = resultSet.getBigDecimal(OrderDetails.COLUMN_PRICE);
        return new OrderDetails(id, orderId, medicamentId, amount, totalPrice);
    }
}
