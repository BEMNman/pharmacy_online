package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDao;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.util.InputDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class RecipeService {

    private static final Logger logger = LogManager.getLogger(UserService.class.getName());

    private RecipeDao recipeDao;

    public RecipeService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            recipeDao = daoHelper.createRecipeDao();
        } catch (DaoException e) {
            logger.warn("RecipeDao wasn't created " + e);

            throw new ServerException(e);
        }
    }

    public List<Recipe> findAllRecipesUserForMedicamentCurrentDate(Long userId, Long medicamentId)
            throws ServerException {
        try {
            return recipeDao.getAllUsersRecipesForMedicamentForCurrentDate(userId, medicamentId);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void saveNewRecipe(User user, String stringMedicamentId, String stringPatientId, String stringQuantity,
                              String stringExpDate) throws ServerException {
        try {
            if (InputDataValidator.notNullOrEmpty(stringMedicamentId, stringPatientId, stringQuantity, stringExpDate)) {
                Long medicamentId = Long.parseLong(stringMedicamentId);
                Long patientId = Long.parseLong(stringPatientId);
                Integer quantity = Integer.parseInt(stringQuantity);
                LocalDate expDate = LocalDate.parse(stringExpDate);
                if (medicamentId > 0 && patientId > 0 && quantity >= 0 && expDate.isAfter(LocalDate.now())) {
                    Recipe recipe = Recipe.newRecipe(expDate, medicamentId, quantity, patientId, user.getId());
                    recipeDao.save(recipe);
                }
            }
        } catch (IllegalArgumentException | DaoException e) {
            throw new ServerException(e);
        }
    }
}
