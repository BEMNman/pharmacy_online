package com.epam.finalproject.pharmacy.dao.medicament;

import com.epam.finalproject.pharmacy.dao.AbstractDao;
import com.epam.finalproject.pharmacy.mapper.MedicamentRowMapper;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MedicamentDaoImpl extends AbstractDao<Medicament> implements MedicamentDao {

    public static final String JOIN_ORDER_DETAILS_AND_ORDER =
            "SELECT m.id, m.name, m.form, m.dosage, m.recipe, m.amountInPack, od.price, od.quantity, m.archive " +
                    "FROM orderdetails AS od " +
                    "JOIN medicines AS m ON od.medicamentId=m.id " +
                    "JOIN orders AS o ON o.id = od.orderId " +
                    "WHERE o.userId=? AND od.orderId=?";

    public static final String QUERY_TO_CHECK_QUANTITY =
            "SELECT COUNT(m.id) FROM medicines m WHERE m.id = ? AND m.quantity >= ?";

    public static final String QUERY_TO_CHECK_RECIPE =
            "SELECT COUNT(r.id) FROM recipes r " +
                    "WHERE r.medicamentId = ? " +
                    "AND r.patientId = ? AND TO_DAYS(r.expDate) > TO_DAYS(NOW()) AND r.amount >= ? ";

    private static final String SEND_MEDICAMENT_TO_ARCHIVE = "UPDATE medicines  SET archive = '1' WHERE id = ?";

    private static final String SELECT_ALL_AVAILABLE_MEDICINES_FOR_PAGE =
            "SELECT * FROM medicines WHERE archive = 0 " +
                    "ORDER BY name, dosage LIMIT ? OFFSET ?";

    private static final String ALL_MEDICINES_WITH_RECIPE =
            "SELECT * FROM medicines WHERE recipe = 1 AND archive = 0 " +
                    "ORDER BY name, dosage";
    private static final String SELECT_ALL_AVAILABLE_MEDICINES = "SELECT * FROM medicines WHERE archive = 0";

    public static final int INT_TRUE = 1;
    public static final int INT_FALSE = 0;

    public MedicamentDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected Map<String, Object> getFieldsValues(Medicament medicament) {
        Map<String, Object> mapFieldsValues = new LinkedHashMap<>();
        mapFieldsValues.put(Medicament.COLUMN_NAME, medicament.getName());
        mapFieldsValues.put(Medicament.COLUMN_FORM, medicament.getForm().name());
        mapFieldsValues.put(Medicament.COLUMN_DOSAGE, medicament.getDosage());
        mapFieldsValues.put(Medicament.COLUMN_RECIPE, medicament.isRecipe() ? INT_TRUE : INT_FALSE);
        mapFieldsValues.put(Medicament.COLUMN_AMOUNT_IN_PACK, medicament.getAmountInPack());
        mapFieldsValues.put(Medicament.COLUMN_PRICE, medicament.getPrice());
        mapFieldsValues.put(Medicament.COLUMN_QUANTITY_IN_STOCK, medicament.getQuantity());
        mapFieldsValues.put(Medicament.COLUMN_ARCHIVE, medicament.isArchive() ? INT_TRUE : INT_FALSE);

        return mapFieldsValues;
    }

    @Override
    protected String getTableName() {
        return Medicament.NAME_TABLE_IN_DB;
    }

    @Override
    public List<Medicament> findAllMedicamentForUsersOrder(Long userId, Long orderId) throws DaoException {
        return executeQuery(JOIN_ORDER_DETAILS_AND_ORDER, new MedicamentRowMapper(), userId, orderId);
    }

    @Override
    public List<Medicament> findAllMedicinesWithRecipe() throws DaoException {
        return executeQuery(ALL_MEDICINES_WITH_RECIPE, new MedicamentRowMapper());
    }

    @Override
    public boolean checkQuantityInStock(Long medicamentId, Integer quantity) throws DaoException {
        return checkExistingRow(QUERY_TO_CHECK_QUANTITY, medicamentId, quantity);
    }

    @Override
    public boolean checkAvailableRecipe(Long userId, Long medicamentId, Integer tempTotalQuantity) throws DaoException {
        return checkExistingRow(QUERY_TO_CHECK_RECIPE, medicamentId, userId, tempTotalQuantity);
    }

    @Override
    public void sendMedicamentToArchive(Long id) throws DaoException {
        update(SEND_MEDICAMENT_TO_ARCHIVE, id);
    }

    @Override
    public List<Medicament> findAllAvailableMedicamentForRequestedPage(int startRow, int count) throws DaoException {
        return executeQuery(SELECT_ALL_AVAILABLE_MEDICINES_FOR_PAGE, new MedicamentRowMapper(), count, startRow);
    }

    @Override
    public List<Medicament> calculateRowAvailableMedicines() throws DaoException {
        return executeQuery(SELECT_ALL_AVAILABLE_MEDICINES, new MedicamentRowMapper(), null);
    }
}
