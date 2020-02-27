package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.medicament.MedicamentDao;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.MedicamentForm;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.NotAvailableActionException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.util.InputDataValidator;

import java.math.BigDecimal;
import java.util.*;

public class MedicamentService {

    private static final String NOT_AVAILABLE_RECIPE = "message.not_available_recipe";
    private static final String NOT_ENOUGH_IN_STOCK = "message.not_enough_in_stock";
    private static final String ENTERED_DATA_ARE_INCORRECT = "message.entered_data_incorrect";
    private static final String ENTERED_QUANTITY_MORE_THAN_IN_RECIPE = "message.entered_quantity_recipe";

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
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RecipeDao recipeDao = daoHelper.createRecipeDao();
            return recipeDao.getAllUsersRecipesForMedicamentForCurrentDate(userId, medicament.getId());
        } catch (DaoException e) {
            throw new ServerException(e);
        }
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

    public Map<Medicament, Integer> recountOrder(User user, Map<Medicament, Integer> medicinesInBasket, List<Integer> inputQuantityMedicines)
            throws ServerException, NotAvailableActionException {
        Map<Medicament, Integer> checkedBasket = new HashMap<>(medicinesInBasket);
        int indexCount = 0;
        for (Medicament medicament : checkedBasket.keySet()) {
            checkedBasket.put(medicament, inputQuantityMedicines.get(indexCount++));
        }
        checkedBasket.values().removeIf(value -> value == 0);
        boolean isQuantityCorrect = checkQuantityMedicinesInUsersOrder(checkedBasket);
        if (!isQuantityCorrect) {
            throw new NotAvailableActionException(NOT_ENOUGH_IN_STOCK);
        }
        for (Medicament medicament : checkedBasket.keySet()) {
            if (medicament.isRecipe()) {
                Integer realQuantity = checkedBasket.get(medicament);
                boolean hasRecipeForQuantity = checkOrderForHavingRecipes(user.getId(), medicament, realQuantity);
                if (!hasRecipeForQuantity) {
                    throw new NotAvailableActionException(ENTERED_QUANTITY_MORE_THAN_IN_RECIPE);
                }
            }
        }
        return checkedBasket;
    }

    public void addMedicamentInBasket(User user, String stringMedicamentId, String stringCount,
                                      Map<Medicament, Integer> medicamentCount)
            throws ServerException, NotAvailableActionException {
        Long medicamentId = Long.parseLong(stringMedicamentId);
        Optional<Medicament> optionalMedicament = findMedicamentById(medicamentId);
        Medicament medicament = null;
        if (optionalMedicament.isPresent()) {
            medicament = optionalMedicament.get();
        } else {
            throw new ServerException("Medicament wasn't found");
        }
        Integer quantityMedicamentBasket = medicamentCount.getOrDefault(medicament, 0);
        Integer countMedicament = Integer.parseInt(stringCount);
        Integer tempTotalQuantity = countMedicament + quantityMedicamentBasket;
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            boolean hasEnoughQuantity = medicamentDao.checkQuantityInStock(medicamentId, tempTotalQuantity);
            if (hasEnoughQuantity) {
                boolean availableRecipe = medicamentDao.checkAvailableRecipe(user.getId(), medicamentId, tempTotalQuantity);
                if (medicament.isRecipe() && !availableRecipe) {
                    throw new NotAvailableActionException(NOT_AVAILABLE_RECIPE);
                }
            } else {
                throw new NotAvailableActionException(NOT_ENOUGH_IN_STOCK);
            }
            medicamentCount.put(medicament, tempTotalQuantity);
        } catch (DaoException e) {
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

    public void updateMedicament(Long id, String stringName, String stringForm, String stringDosage, boolean recipe,
                                 String stringAmountInPack, String stringPrice, String stringQuantity)
            throws ServerException, NotAvailableActionException {
        InputDataValidator inputDataValidator = new InputDataValidator();
        if (inputDataValidator.notNullOrEmpty(stringName, stringForm, stringDosage,
                stringAmountInPack, stringPrice, stringQuantity)) {
            try (DaoHelper daoHelper = daoHelperFactory.create()) {
                MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
                MedicamentForm form = MedicamentForm.valueOf(stringForm.toUpperCase());
                Integer amountInPack = Integer.parseInt(stringAmountInPack);
                BigDecimal price = new BigDecimal(stringPrice);
                Integer quantity = Integer.parseInt(stringQuantity);

                if (price.compareTo(BigDecimal.valueOf(0)) <= 0 || quantity < 0 || amountInPack <= 0) {
                    throw new NotAvailableActionException(ENTERED_DATA_ARE_INCORRECT);
                }
                Medicament medicament =
                        Medicament.newMedicament(id, stringName, form, stringDosage, recipe, amountInPack, price, quantity);
                medicamentDao.save(medicament);
            } catch (IllegalArgumentException | DaoException e) {
                throw new ServerException(e);
            }
        } else {
            throw new NotAvailableActionException(ENTERED_DATA_ARE_INCORRECT);
        }
    }

    public int calculateRowsAvailableMedicines() throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();
            return medicamentDao.calculateRowAvailableMedicines().size();
        } catch (DaoException e) {
            throw new ServerException(e);
        }

    }

    public Map<Medicament, Integer> refreshBasket(Map<Medicament, Integer> medicinesAndCountInBasket) throws ServerException {
        Map<Medicament, Integer> refreshedBasket = new LinkedHashMap<>();
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MedicamentDao medicamentDao = daoHelper.createMedicamentDao();

            for (Medicament medicament : medicinesAndCountInBasket.keySet()) {
                Optional<Medicament> optionalMedicament = medicamentDao.findById(medicament.getId());
                if(!optionalMedicament.isPresent()) {
                    throw new ServerException("Can't find medicament");
                }
                Medicament refreshedMedicament = optionalMedicament.get();
                refreshedBasket.put(refreshedMedicament, medicinesAndCountInBasket.get(medicament));
            }
            return refreshedBasket;
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
