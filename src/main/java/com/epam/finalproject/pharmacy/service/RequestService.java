package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.request.RequestDao;
import com.epam.finalproject.pharmacy.entity.Request;
import com.epam.finalproject.pharmacy.entity.RequestStatus;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;

import java.util.Optional;

public class RequestService {
    private RequestDao requestDao;

    public RequestService (DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            requestDao = daoHelper.createRequestDao();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void sendRequest(String recipeId, String requestedPeriod) throws ServerException {
        Long id = Long.parseLong(recipeId);
        Integer period = Integer.parseInt(requestedPeriod);
        Request request = Request.newRequest(id, period);
        try {
            requestDao.save(request);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void rejectRequest(Long requestId) throws ServerException {
        try {
            Optional<Request> optionalRequest = requestDao.findById(requestId);
            if(optionalRequest.isPresent()) {
                Request request = optionalRequest.get();
                request.setStatus(RequestStatus.CLOSED);
                requestDao.save(request);
            }
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
