package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDao;
import com.epam.finalproject.pharmacy.entity.Recipe;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}
