package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.request.RequestDtoDao;
import com.epam.finalproject.pharmacy.dto.RequestDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;

import java.util.List;

public class RequestDtoService {

    private RequestDtoDao requestDtoDao;

    public RequestDtoService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            requestDtoDao = daoHelper.createRequestDtoDao();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<RequestDto> findAllRequestsDtoForDoctor(User user) throws ServerException {
        try {
            return requestDtoDao.findAllRequestsDtoForDoctor(user);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
