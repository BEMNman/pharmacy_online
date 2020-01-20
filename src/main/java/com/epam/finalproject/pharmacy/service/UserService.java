package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.user.UserDao;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class.getName());

    private UserDao userDao;

    public UserService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            this.userDao = daoHelper.createUserDao();
        } catch (DaoException e) {
            logger.warn("UserDao wasn't created "+ e);

            throw new ServerException(e);
        }
    }

    public Optional<User> login(String login, String password) throws ServerException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            logger.warn("Can't find user with correct login and password"+ e);

            throw new ServerException(e);
        }
    }

    public void registerNewUserByPacient(User pacient) throws ServerException {
        try {
            userDao.save(pacient);
        } catch (DaoException e) {
            logger.warn("Can't save user in DB: "+ e);

            throw new ServerException(e);
        }

    }
}
