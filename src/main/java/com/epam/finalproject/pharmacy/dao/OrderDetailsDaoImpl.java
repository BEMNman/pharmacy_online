package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.entity.OrderDetails;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.Connection;

public class OrderDetailsDaoImpl extends AbstractDao<OrderDetails> implements OrderDetailsDao {

    private static final String SAVE_DETAILS_FOR_ORDER = "INSERT INTO orderdetails VALUE((SELECT id )?)";

    public OrderDetailsDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getValuesItemByStringForQuery(OrderDetails item) {
        StringBuilder fieldsValuesForQuery = new StringBuilder();
        fieldsValuesForQuery.append("'")
                .append(item.getOrderId()).append("', '")
                .append(item.getMedicamentId()).append("', '")
                .append(item.getQuantity()).append("', '")
                .append(item.getPrice()).append("'");
        return fieldsValuesForQuery.toString();
    }

    @Override
    protected String getTableName() {
        return OrderDetails.NAME_TABLE_IN_DB;
    }

    @Override
    public void saveDetailsForOrder(Order order, OrderDetails orderDetails) throws DaoException {
    }

    @Override
    public void removeById(Long id) throws DaoException {

    }
}
