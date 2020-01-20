package com.epam.finalproject.pharmacy.dao.user;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
