package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.MedicamentDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MedicamentService {
    private MedicamentDao medicamentDao;

    public MedicamentService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            this.medicamentDao = daoHelper.createMedicamentDao();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Medicament> showAll() throws ServerException {
        try {
            return medicamentDao.getAll();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public Optional<Medicament> findMedicamentById(long id) throws ServerException {
        try {
            return medicamentDao.getById(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void save(Medicament medicament) throws ServerException {
        try {
            medicamentDao.save(medicament);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public Map<Medicament, Integer> createMapMedicamentsAmount(List<Long> listIdItems) throws ServerException {
        Map<Medicament, Integer> medicinesInOrder = new Hashtable<>();
        for (Long id : listIdItems) {
            Optional<Medicament> optionalMedicament = findMedicamentById(id);
            if (optionalMedicament.isPresent()) {
                Medicament medicament = optionalMedicament.get();
                if (medicinesInOrder.containsKey(medicament)) {
                    Integer amountMedicament = medicinesInOrder.get(medicament);
                    medicinesInOrder.put(medicament, ++amountMedicament);
                } else {
                    medicinesInOrder.put(medicament, 1);
                }
            }
        }
        return medicinesInOrder;
    }

    public List<Medicament> findMedicamentForOrder(long orderId) throws ServerException {
        try {
            return medicamentDao.getAllMedicamentForOrder(orderId);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
