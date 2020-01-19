package com.epam.finalproject.pharmacy.builder;

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

        String lockedString = resultSet.getString(User.COLUMN_LOCKED);
        boolean locked = lockedString.equals("1");
        return new User(id, name, login, password, role, locked);
    }

    @Override
    public String getFieldsMapperByStringForQuery() {
        StringBuilder fieldsString = new StringBuilder();
        fieldsString.append(User.COLUMN_NAME).append(", ")
                .append(User.COLUMN_LOGIN).append(", ")
                .append(User.COLUMN_PASSWORD).append(", ")
                .append(User.COLUMN_ROLE).append(", ")
                .append(User.COLUMN_LOCKED);
        return fieldsString.toString();
    }
}
