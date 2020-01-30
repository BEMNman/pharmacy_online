package com.epam.finalproject.pharmacy.dao.request;

import com.epam.finalproject.pharmacy.dto.RequestDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

public interface RequestDtoDao {
    List<RequestDto> findAllRequestsDtoForDoctor(User user) throws DaoException;
}
