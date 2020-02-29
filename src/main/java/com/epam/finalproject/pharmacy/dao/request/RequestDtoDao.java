package com.epam.finalproject.pharmacy.dao.request;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.dto.RequestDto;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

/**
 * The interface extends an {@link Dao}.
 * The interface contains description the single method for
 * working with the database for objects {@link RequestDto}.
 *
 * @author Gogolinsky
 * @see Dao
 * @see RequestDto
 */

public interface RequestDtoDao extends Dao<RequestDto> {

    /**
     * The method finds all recipe {@link com.epam.finalproject.pharmacy.entity.Recipe}
     * renewal request {@link com.epam.finalproject.pharmacy.entity.Request}
     * in the database for <code>user</code>.
     *
     * @param user type is {@link User}.
     * @return {@link List} with objects {@link RequestDto} which belong to the <code>user</code>.
     * If objects {@link RequestDto} weren't found, the list will be empty.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<RequestDto> findAllRequestsDtoForDoctor(User user) throws DaoException;
}
