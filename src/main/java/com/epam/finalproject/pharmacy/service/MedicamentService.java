package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.medicament.MedicamentDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;

import java.util.*;

public class MedicamentService {

    public static final String MESSAGE_NOT_AVAILABLE_RECIPE = "You don't have recipe for this medicament";
    public static final String MESSAGE_NOT_ENOUGH_IN_STOCK = "Entered quantity is more than in stock";
    public static final String MESSAGE_EMPTY = "";

    private MedicamentDao medicamentDao;

    public MedicamentService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            medicamentDao = daoHelper.createMedicamentDao();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Medicament> showAll() throws ServerException {
        try {
            return medicamentDao.findAll();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public Optional<Medicament> findMedicamentById(Long id) throws ServerException {
        try {
            return medicamentDao.findById(id);
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

    public List<Medicament> findMedicamentForOrder(Long orderId) throws ServerException {
        try {
            return medicamentDao.findAllMedicamentForOrder(orderId);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    private boolean checkQuantityMedicinesInUsersOrder(Map<Medicament, Integer> mapMedicinesCount)
            throws ServerException {
        for (Medicament medicament : mapMedicinesCount.keySet()) {
            if (medicament.getQuantity() < mapMedicinesCount.get(medicament)) {
                return false;
            }
        }
        return true;
    }
//    public boolean checkRecipesAvailable(User user, Medicament medicament) throws ServerException {
//        if (medicament.isRecipe()) {
//            List<Recipe> recipes = getActualRecipesForUserMedicament(user.getId(), medicament);
//            return recipes.size() > 0;
//        } else {
//            return true;
//        }
//    }
    private List<Recipe> getActualRecipesForUserMedicament(Long userId, Medicament medicament) throws ServerException {
        RecipeService recipeService = new RecipeService(new DaoHelperFactory());
        return recipeService.findAllRecipesUserForMedicamentCurrentDate(userId, medicament.getId());
    }


    private boolean checkOrderForHavingRecipes(Long userId, Medicament medicament, Integer realQuantity)
            throws ServerException {
        List<Recipe> actualRecipes = getActualRecipesForUserMedicament(userId, medicament);
        for (Recipe recipe : actualRecipes) {
            if (realQuantity <= recipe.getAmount()) {
                return true;
            }
        }
        return false;
    }

    public String checkOrder(User user, Map<Medicament, Integer> medicinesCount, String[] count) throws ServerException {
        int indexCount = 0;
        for (Medicament medicament : medicinesCount.keySet()) {
            Integer quantity = Integer.parseInt(count[indexCount++]);
            medicinesCount.put(medicament, quantity);
        }
        medicinesCount.values().removeIf(value -> value == 0);
        boolean isQuantityCorrect = checkQuantityMedicinesInUsersOrder(medicinesCount);
        if (!isQuantityCorrect) {
            return "Quantity more than in the stock";
        }

        for (Medicament medicament : medicinesCount.keySet()) {
            if (medicament.isRecipe()) {
                Integer realQuantity = medicinesCount.get(medicament);
                boolean hasRecipeForQuantity = checkOrderForHavingRecipes(user.getId(), medicament, realQuantity);
                if (!hasRecipeForQuantity) {
                    return "Entered quantity for " + medicament.getName() + " is more than quantity specified in recipe";
                }
            }
        }
        return new String();
    }

    public String checkQuantityInStock(User user, String stringMedicamentId, String stringCount,
                                       Map<Medicament, Integer> medicamentCount) throws ServerException {
        Long medicamentId = Long.parseLong(stringMedicamentId);
        try {
            Medicament medicament = findMedicamentById(medicamentId).get();
            Integer quantityMedicamentBasket = medicamentCount.getOrDefault(medicament, 0);
            Integer countMedicament = Integer.parseInt(stringCount);
            Integer tempTotalQuantity = countMedicament + quantityMedicamentBasket;
            boolean hasEnoughQuantity = medicamentDao.checkQuantityInStock(medicamentId, tempTotalQuantity);
            if (hasEnoughQuantity) {
                boolean availableRecipe = medicamentDao.checkAvailableRecipe(user.getId(), medicamentId, tempTotalQuantity);
                if (medicament.isRecipe() && !availableRecipe) {
                    return MESSAGE_NOT_AVAILABLE_RECIPE;
                }
            } else {
                return MESSAGE_NOT_ENOUGH_IN_STOCK;
            }
            medicamentCount.put(medicament, tempTotalQuantity);
            return MESSAGE_EMPTY;
        } catch (DaoException e) {
            throw new ServerException(e);
        }

    }

    public void deleteMedicamentById(Long id) throws ServerException {
        try {
            medicamentDao.sendMedicamentToArchive(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
