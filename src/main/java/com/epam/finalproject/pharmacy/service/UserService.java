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
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            userDao = daoHelper.createUserDao();
        } catch (DaoException e) {
            logger.warn("UserDao wasn't created " + e);

            throw new ServerException(e);
        }
    }

    public Optional<User> login(String login, String password) throws ServerException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            logger.warn("Can't find user with correct login and password" + e);

            throw new ServerException(e);
        }
    }

    public boolean registerNewUserByPatient(String patientName, String login, String password, String passwordForCheck)
            throws ServerException {
        if (validRegistrationData(login, password, passwordForCheck)) {
            User patient = User.newPatient(patientName, login, password);
            try {
                userDao.save(patient);
                return  true;
            } catch (DaoException e) {
                logger.warn("Can't save user in DB: " + e);
                throw new ServerException(e);
            }
        }
        return false;
    }

    private boolean validRegistrationData(String login, String password, String passwordForCheck) {
        return login != null && !login.isEmpty()
                && password != null && !password.isEmpty()
                && password.equals(passwordForCheck);
    }
}
