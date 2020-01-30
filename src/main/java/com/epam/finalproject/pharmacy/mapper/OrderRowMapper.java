package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.entity.OrderStatus;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Order.COLUMN_ID);
        LocalDateTime creationDate = resultSet.getTimestamp(Order.COLUMN_CREATION_DATE).toLocalDateTime();
        long userId = resultSet.getLong(Order.COLUMN_USER_ID);
        BigDecimal totalPrice = resultSet.getBigDecimal(Order.COLUMN_PRICE);
        String statusString = resultSet.getString(Order.COLUMN_STATUS);
        OrderStatus status = OrderStatus.valueOf(statusString);
        return new Order(id, creationDate, userId, totalPrice, status);
    }
}
