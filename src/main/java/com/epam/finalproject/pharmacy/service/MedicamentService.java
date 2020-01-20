package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.medicament.MedicamentDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;

import java.util.*;

public class MedicamentService {
    private MedicamentDao medicamentDao;

    public MedicamentService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            this.medicamentDao = daoHelper.createMedicamentDao();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Medicament> showAll() throws ServerException {
        try {
            return medicamentDao.getAll();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public Optional<Medicament> findMedicamentById(long id) throws ServerException {
        try {
            return medicamentDao.getById(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void save(Medicament medicament) throws ServerException {
        try {
            medicamentDao.save(medicament);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public Map<Medicament, Integer> createMapMedicinesQuantity(List<Long> listIdItems) throws ServerException {
        Map<Medicament, Integer> medicinesInOrder = new LinkedHashMap<>();
        for (Long id : listIdItems) {
            Optional<Medicament> optionalMedicament = findMedicamentById(id);
            if (optionalMedicament.isPresent()) {
                Medicament medicament = optionalMedicament.get();
                if (medicinesInOrder.containsKey(medicament)) {
                    Integer amountMedicament = medicinesInOrder.get(medicament);
                    medicinesInOrder.put(medicament, ++amountMedicament);
                } else {
                    medicinesInOrder.put(medicament, 1);
                }
            }
        }
        return medicinesInOrder;
    }

    public List<Medicament> findMedicamentForOrder(Long orderId) throws ServerException {
        try {
            return medicamentDao.getAllMedicamentForOrder(orderId);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public boolean checkMedicinesInUsersOrder(Long userId, Long orderId) throws ServerException {
        List<Medicament> medicinesInOrder = findMedicamentForOrder(orderId);
        for (Medicament medicament : medicinesInOrder) {
            Long medicamentId = medicament.getId();
            try {
                Optional<Medicament> medicamentTemp = medicamentDao.getById(medicamentId);
                Medicament medicamentFromStock = null;
                if (medicamentTemp.isPresent()) {
                    medicamentFromStock = medicamentTemp.get();
                } else {
                    return false;
                }
                if (checkQuantityMedicines(medicament, medicamentFromStock)) {
                    if (medicament.isRecipe()) {
                        if (getActualRecipes(userId, medicamentId).size() == 0) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } catch (DaoException e) {
                throw new ServerException(e);
            }
        }
        return true;
    }

    private List<Recipe> getActualRecipes(Long userId, Long medicamentId) throws ServerException {
        RecipeService recipeService = new RecipeService(new DaoHelperFactory());
        return recipeService.findAllRecipesUserForMedicamentForCurrentDate(userId, medicamentId);
    }

    private boolean checkQuantityMedicines(Medicament medicament, Medicament medicamentFromStock) {
        return medicament.getQuantity() <= medicamentFromStock.getQuantity();
    }
}
