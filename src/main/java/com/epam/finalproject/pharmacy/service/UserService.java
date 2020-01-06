package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.UserDao;
import com.epam.finalproject.pharmacy.entity.Client;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;

import java.util.Optional;

public class UserService {
    public Optional<Client> login(String login, String password) throws ServerException {
        UserDao dao = new UserDao();
        try {
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
