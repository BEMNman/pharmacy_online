package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.entity.Identifable;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifable> {
    Optional<T> getById(Long id) throws DaoException;
    List<T> getAll() throws DaoException;
    void save(T item) throws DaoException;
    void removeById(Long id) throws DaoException;
}
