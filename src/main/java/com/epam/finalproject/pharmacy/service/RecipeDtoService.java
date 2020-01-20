package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDao;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDtoDao;
import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RecipeDtoService {

    private static final Logger logger = LogManager.getLogger(RecipeDtoService.class.getName());

    private RecipeDtoDao recipeDao;

    public RecipeDtoService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            this.recipeDao = daoHelper.createRecipeDtoDao();
        } catch (DaoException e) {
            logger.warn("RecipeDao wasn't created " + e);

            throw new ServerException(e);
        }
    }

    public List<RecipeDto> findAllRecipesDtoForUser(User user) throws ServerException {
        Long userId = user.getId();
        try {
            return recipeDao.getAllRecipesDtoForUser(userId);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
