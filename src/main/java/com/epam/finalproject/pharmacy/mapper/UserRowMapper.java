package com.epam.finalproject.pharmacy.mapper;

import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(User.COLUMN_ID);
        String name = resultSet.getString(User.COLUMN_NAME);
        String login = resultSet.getString(User.COLUMN_LOGIN);
        String password = resultSet.getString(User.COLUMN_PASSWORD);
        String roleTypeByString = resultSet.getString(User.COLUMN_ROLE);
        UserRole role = UserRole.valueOf(roleTypeByString.toUpperCase());
        return new User(id, name, login, password, role);
    }
}
