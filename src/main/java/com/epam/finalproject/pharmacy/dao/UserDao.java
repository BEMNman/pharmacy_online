package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.entity.Client;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.Optional;

public class UserDao {

    public Optional<Client> findUserByLoginAndPassword(String login, String password) throws DaoException {
        if(login.equals("admin") && password.equals("admin")) {
            return Optional.of(new Client("Ivan"));
        } else {
            return Optional.empty();
        }
    }
}
