package com.epam.finalproject.pharmacy.dao.order;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.mapper.OrderRowMapper;
import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    private static final String FIND_ALL_ORDER_FOR_USER_BY_ID = "SELECT * FROM orders " +
            "WHERE userId = ? " +
            "ORDER BY creationDate DESC";

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected Map<String, Object> getFieldsValues(Order item) {
        Map<String, Object> mapFieldsValues = new LinkedHashMap<>();
        mapFieldsValues.put(Order.COLUMN_CREATION_DATE, item.getCreationDate());
        mapFieldsValues.put(Order.COLUMN_USER_ID, item.getUserId());
        mapFieldsValues.put(Order.COLUMN_PRICE, item.getPrice());
        mapFieldsValues.put(Order.COLUMN_STATUS, item.getStatus().name());
        return mapFieldsValues;
    }

    @Override
    protected String getTableName() {
        return Order.NAME_TABLE_IN_DB;
    }

    @Override
    public Long saveAndGetIdLastSavedOrder(Order order) throws DaoException {
        String query = buildQueryForSave(order);
        try (PreparedStatement ps = prepareStatementFoReturnGeneratedId(query)) {
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return Long.valueOf(rs.getInt(1));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> getAllOrdersForUser(Long userId) throws DaoException {
        return executeQuery(FIND_ALL_ORDER_FOR_USER_BY_ID, new OrderRowMapper(), userId);
    }
}
