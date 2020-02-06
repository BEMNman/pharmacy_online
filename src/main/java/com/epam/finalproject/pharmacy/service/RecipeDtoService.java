package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDtoDao;
import com.epam.finalproject.pharmacy.dto.RecipeDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;

import java.util.List;

public class RecipeDtoService {

    private DaoHelperFactory daoHelperFactory;

    public RecipeDtoService(DaoHelperFactory daoHelperFactory) {
       this.daoHelperFactory = daoHelperFactory;
    }

    public List<RecipeDto> findAllRecipesDtoForUser(User user) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RecipeDtoDao recipeDao = daoHelper.createRecipeDtoDao();
            return recipeDao.getAllRecipesDtoForUser(user);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
