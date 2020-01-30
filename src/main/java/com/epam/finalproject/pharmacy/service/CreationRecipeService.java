package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.medicament.MedicamentDao;
import com.epam.finalproject.pharmacy.dao.user.UserDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;

import java.util.List;

public class CreationRecipeService {

    private MedicamentDao medicamentDao;
    private UserDao userDao;

    public CreationRecipeService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            medicamentDao = daoHelper.createMedicamentDao();
            userDao = daoHelper.createUserDao();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<User> findAllUserByRole(UserRole role) throws ServerException {
        try {
            return userDao.findAllUserByRole(role);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Medicament> findAllMedicinesWithRecipe() throws ServerException {
        try {
            return medicamentDao.findAllMedicinesWithRecipe();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
