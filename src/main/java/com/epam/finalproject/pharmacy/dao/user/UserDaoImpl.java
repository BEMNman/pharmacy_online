package com.epam.finalproject.pharmacy.dao.user;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.mapper.UserRowMapper;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM users WHERE login = ? AND password = MD5(?) AND locked = false";
    private static final String FIND_ALL_USER_BY_ROLE = "SELECT * FROM users WHERE role = ? ORDER BY name";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    protected Map<String, Object> getFieldsValues(User user) {
        Map<String, Object> mapFieldsValues = new LinkedHashMap<>();
        mapFieldsValues.put(User.COLUMN_NAME, user.getName());
        mapFieldsValues.put(User.COLUMN_LOGIN, user.getLogin());
        mapFieldsValues.put(User.COLUMN_PASSWORD, "MD5('"+user.getPassword()+"')");
        mapFieldsValues.put(User.COLUMN_ROLE, user.getRole().name());
        return mapFieldsValues;
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    @Override
    public List<User> findAllUserByRole(UserRole role) throws DaoException {
        return executeQuery(FIND_ALL_USER_BY_ROLE, new UserRowMapper(), role.name());
    }

    @Override
    protected String getTableName() {
        return User.NAME_TABLE_IN_DB;
    }
}
