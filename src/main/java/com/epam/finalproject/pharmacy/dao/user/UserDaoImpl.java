package com.epam.finalproject.pharmacy.dao.user;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.mapper.UserRowMapper;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = MD5(?) AND locked = false";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getValuesItemByStringForQuery(User user) { //get fieldsvalues return map<string, obj>
        StringBuilder fieldsValuesForQuery = new StringBuilder();
        fieldsValuesForQuery.append("'")
                .append(user.getName()).append("', '")
                .append(user.getLogin()).append("', ")
                .append("MD5('").append(user.getPassword()).append("')").append(", '")
                .append(user.getRole().name()).append("', '")
                .append(user.isLocked() ? 1 : 0).append("'");
        return fieldsValuesForQuery.toString();
    }

    /*
    new method - getVAluesItemByStringForQuery()
     */
    protected Map<String, Object> getFieldsValues(User user) {
        Map<String, Object> mapFieldsValues = new LinkedHashMap<>();
        mapFieldsValues.put(User.COLUMN_NAME, user.getName());
        mapFieldsValues.put(User.COLUMN_LOGIN, user.getLogin());
        mapFieldsValues.put(User.COLUMN_PASSWORD, user.getName());
        mapFieldsValues.put(User.COLUMN_ROLE, user.getRole().name());
        mapFieldsValues.put(User.COLUMN_LOCKED, user.isLocked());
        return mapFieldsValues;
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    @Override
    protected String getTableName() {
        return User.NAME_TABLE_IN_DB;
    }

    @Override
    public void removeById(Long id) {

    }
}
