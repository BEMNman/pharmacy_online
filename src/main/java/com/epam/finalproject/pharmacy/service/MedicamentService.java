package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.medicament.MedicamentDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.MedicamentForm;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.NotFoundItemException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.util.InputDataValidator;

import java.math.BigDecimal;
import java.util.*;

public class MedicamentService {

    private static final String MESSAGE_NOT_AVAILABLE_RECIPE = "message.not_available_recipe";
    private static final String MESSAGE_NOT_ENOUGH_IN_STOCK = "message.not_enough_in_stock";
    private static final String MESSAGE_EMPTY = "";
    private static final String ENTERED_DATA_ARE_INCORRECT = "message.entered_data_incorrect";


    private DaoHelperFactory daoHelperFactory;

    public MedicamentService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Medicament> showMedicinesOnPage(int startRow, int count) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            return medicamentDao.findAllAvailableMedicamentForRequestedPage(startRow, count);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public Optional<Medicament> findMedicamentById(Long id) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            return medicamentDao.findById(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Medicament> findMedicamentForUsersOrder(User user, Long orderId) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            return medicamentDao.findAllMedicamentForUsersOrder(user.getId(), orderId);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    private boolean checkQuantityMedicinesInUsersOrder(Map<Medicament, Integer> mapMedicinesCount) {
        for (Medicament medicament : mapMedicinesCount.keySet()) {
            if (medicament.getQuantity() < mapMedicinesCount.get(medicament)) {
                return false;
            }
        }
        return true;
    }

    private List<Recipe> getActualRecipesForUserMedicament(Long userId, Medicament medicament) throws ServerException {
        RecipeService recipeService = new RecipeService(new DaoHelperFactory());
        return recipeService.findAllRecipesUserForMedicamentCurrentDate(userId, medicament.getId());
    }


    private boolean checkOrderForHavingRecipes(Long userId, Medicament medicament, Integer realQuantity)
            throws ServerException {
        List<Recipe> actualRecipes = getActualRecipesForUserMedicament(userId, medicament);
        for (Recipe recipe : actualRecipes) {
            if (realQuantity <= recipe.getQuantity()) {
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
        return MESSAGE_EMPTY;
    }

    public String addMedicamentInBasket(User user, String stringMedicamentId, String stringCount,
                                        Map<Medicament, Integer> medicamentCount) throws ServerException {
        Long medicamentId = Long.parseLong(stringMedicamentId);
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            Optional<Medicament> optionalMedicament = findMedicamentById(medicamentId);
            Medicament medicament = null;
            if(optionalMedicament.isPresent()) {
                medicament= optionalMedicament.get();
            } else {
                throw new NotFoundItemException("Medicament wasn't found in BD");
            }
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
        } catch (DaoException | NotFoundItemException e) {
            throw new ServerException(e);
        }

    }

    public void deleteMedicamentById(Long id) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            medicamentDao.sendMedicamentToArchive(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public String updateMedicament(Long id, String stringName, String stringForm, String stringDosage, boolean recipe,
                                   String stringAmountInPack, String stringPrice, String stringQuantity) throws ServerException {
        if (InputDataValidator.notNullOrEmpty(stringName, stringForm, stringDosage,
                stringAmountInPack, stringPrice, stringQuantity)) {
            try (DaoHelper daoHelper = daoHelperFactory.create()) {
                MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
                MedicamentForm form = MedicamentForm.valueOf(stringForm.toUpperCase());
                Integer amountInPack = Integer.parseInt(stringAmountInPack);
                BigDecimal price = new BigDecimal(stringPrice);
                Integer quantity = Integer.parseInt(stringQuantity);

                if (price.compareTo(BigDecimal.valueOf(0)) <= 0 || quantity < 0 || amountInPack <= 0) {
                    return ENTERED_DATA_ARE_INCORRECT;
                }
                Medicament medicament =
                        Medicament.newMedicament(id, stringName, form, stringDosage, recipe, amountInPack, price, quantity);
                medicamentDao.save(medicament);
            } catch (IllegalArgumentException | DaoException e) {
                throw new ServerException(e);
            }
        } else {
            return ENTERED_DATA_ARE_INCORRECT;
        }
        return MESSAGE_EMPTY;
    }

    public int calculateRowAvailableMedicines() throws ServerException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            return medicamentDao.calculateRowAvailableMedicines().size();
        } catch (DaoException e) {
            throw new ServerException(e);
        }

    }
}
