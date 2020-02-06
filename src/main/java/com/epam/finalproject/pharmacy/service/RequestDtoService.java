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

    private DaoHelperFactory daoHelperFactory;

    public RequestDtoService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<RequestDto> findAllRequestsDtoForDoctor(User user) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RequestDtoDao requestDtoDao = daoHelper.createRequestDtoDao();
            return requestDtoDao.findAllRequestsDtoForDoctor(user);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
