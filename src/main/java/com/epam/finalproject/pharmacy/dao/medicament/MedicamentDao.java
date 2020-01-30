package com.epam.finalproject.pharmacy.dao.medicament;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

public interface MedicamentDao extends Dao<Medicament> {
    List<Medicament> findAllMedicamentForOrder(long orderId) throws DaoException;

    List<Medicament> findAllAvailableMedicament() throws DaoException;

    boolean checkQuantityInStock(Long medicamentId, Integer count) throws DaoException;

    boolean checkAvailableRecipe(Long userId, Long medicamentId, Integer tempTotalQuantity) throws DaoException;

    void sendMedicamentToArchive(Long id) throws DaoException;

    List<Medicament> findAllMedicinesWithRecipe() throws DaoException;
}
