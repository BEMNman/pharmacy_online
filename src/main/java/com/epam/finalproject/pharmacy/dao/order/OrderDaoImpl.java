package com.epam.finalproject.pharmacy.dao.order;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.mapper.OrderRowMapper;
import com.epam.finalproject.pharmacy.mapper.RowMapper;
import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.*;
import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getValuesItemByStringForQuery(Order item) {
        StringBuilder fieldsValuesForQuery = new StringBuilder();
        fieldsValuesForQuery.append("'")
                .append(item.getCreationDate()).append("', '")
                .append(item.getUserId()).append("', '")
                .append(item.getPrice()).append("', '")
                .append(item.getStatus().name()).append("'");
        return fieldsValuesForQuery.toString();
    }

    @Override
    protected String getTableName() {
        return Order.NAME_TABLE_IN_DB;
    }

    @Override
    public long saveAndGetIdLastSavedOrder(Order order) throws DaoException {
        String query = "INSERT INTO "
                + getTableName() + "(" + RowMapper.create(getTableName()).getFieldsMapperByStringForQuery() + ")"
                + " VALUES(" + getValuesItemByStringForQuery(order) + ")";

        try (PreparedStatement ps = prepareStatementFoReturnGeneratedId(query)) {
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> getAllOrdersForUser(long userId) throws DaoException {
        String query = "SELECT * FROM " + getTableName() + " WHERE userId = ?";

        return executeQuery(query, new OrderRowMapper(), userId);
    }
}
