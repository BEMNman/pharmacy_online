package com.epam.finalproject.pharmacy.dao.medicament;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MedicamentDao extends Dao<Medicament> {
    List<Medicament> getAllMedicamentForOrder(long orderId) throws DaoException;

//    Optional<Medicament> findMedicamentById(Long id) ;
}
