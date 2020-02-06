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

    private DaoHelperFactory daoHelperFactory;

    public CreationRecipeService(DaoHelperFactory daoHelperFactory){
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<User> findAllUserByRole(UserRole role) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            return userDao.findAllUserByRole(role);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Medicament> findAllMedicinesWithRecipe() throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            return medicamentDao.findAllMedicinesWithRecipe();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
