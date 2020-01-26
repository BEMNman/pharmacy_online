package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.mapper.RowMapper;
import com.epam.finalproject.pharmacy.entity.Identifable;
import com.epam.finalproject.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifable> implements Dao<T> {

    private static final Logger logger = LogManager.getLogger(AbstractDao.class.getName());

    private static final String SELECT_ALL_FROM = "SELECT * FROM ";
    private static final String WHERE_VALUE_ID = " WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM ";
    private static final String SAVE_NEW_ITEM_IN_DB = "INSERT INTO ";
    private static final String VALUES = " VALUE(";
    private static final String DELETE_ALL_FROM = "DELETE FROM ";

    private Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            logger.warn("Error SQL query execute: ", e);

            throw new DaoException(e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            if (params != null && params.length != 0) {
                for (int i = 1; i <= params.length; i++) {
                    statement.setObject(i, params[i - 1]);
                }
            }
        } catch (SQLException e) {
            logger.warn("Error SQL query execute: ", e);

            throw new DaoException(e);
        }
        logger.debug("Statement was created and returned: " + statement);

        return statement;
    }

    @Override
    public List<T> findAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery(FIND_ALL + table, mapper);
    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);

        logger.debug("Start finding Item with 'RowMapper': " + mapper + " in table DB + " + table);

        return executeForSingleResult(SELECT_ALL_FROM + table + WHERE_VALUE_ID, mapper, id);
    }

    @Override
    public void save(T item) throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        String stringByItemFields = mapper.getFieldsMapperByStringForQuery();
        String stringByItemValues = getValuesItemByStringForQuery(item);
        String query = SAVE_NEW_ITEM_IN_DB + table + "(" + stringByItemFields + ")" + VALUES + stringByItemValues + ")";
        try (PreparedStatement statement = createStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(String query, Object...params)  throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeById(Long id) throws DaoException {
        String query = DELETE_ALL_FROM + getTableName() + WHERE_VALUE_ID;
        try (PreparedStatement statement = createStatement(query, id)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> builder, Object... params) throws DaoException {
        List<T> items = executeQuery(query, builder, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    protected boolean checkExistingRow(String query, Object...params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int countId = resultSet.getInt(1);
            return countId > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected abstract String getValuesItemByStringForQuery(T item);

    protected abstract String getTableName();

    protected PreparedStatement prepareStatementFoReturnGeneratedId(String query) throws DaoException {
        try {
            return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
