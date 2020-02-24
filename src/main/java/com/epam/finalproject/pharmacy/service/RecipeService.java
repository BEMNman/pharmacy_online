package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDao;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.util.InputDataValidator;

import java.time.LocalDate;

public class RecipeService {

    private DaoHelperFactory daoHelperFactory;

    public RecipeService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void saveNewRecipe(User user, String stringMedicamentId, String stringPatientId, String stringQuantity,
                              String stringExpDate) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            InputDataValidator inputDataValidator = new InputDataValidator();
            if (inputDataValidator.notNullOrEmpty(stringMedicamentId, stringPatientId, stringQuantity, stringExpDate)) {
                Long medicamentId = Long.parseLong(stringMedicamentId);
                Long patientId = Long.parseLong(stringPatientId);
                Integer quantity = Integer.parseInt(stringQuantity);
                LocalDate expDate = LocalDate.parse(stringExpDate);
                if (medicamentId > 0 && patientId > 0 && quantity >= 0 && expDate.isAfter(LocalDate.now())) {
                    RecipeDao recipeDao = daoHelper.createRecipeDao();
                    Recipe recipe = Recipe.newRecipe(expDate, medicamentId, quantity, patientId, user.getId());
                    recipeDao.save(recipe);
                    return;
                }
            }
                throw new ServerException("Date for creating recipe isn't valid");
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
