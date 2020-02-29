package com.epam.finalproject.pharmacy.dao.medicament;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

/**
 * The interface extends an {@link Dao}.
 * The interface contains description the general methods for
 * working with the database for objects {@link Medicament}.
 *
 * @author Gogolinsky
 * @see Dao
 * @see Medicament
 */

public interface MedicamentDao extends Dao<Medicament> {

    /**
     * The method returns list of {@link Medicament}
     * which are in user's order {@link com.epam.finalproject.pharmacy.entity.Order}
     *
     * @param userId  type is {@link Long}.
     *                <code>userId</code> is user's id that ordered medicines.
     *                medicines in order.
     * @param orderId type is {@link Long}.
     * @return list {@link List} with medicines in order.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<Medicament> findAllMedicamentForUsersOrder(Long userId, Long orderId) throws DaoException;

    /**
     * The method compares available quantity medicines in the database
     * and transmitted value for medicament {@link Medicament} with <code>medicamentId</code>.
     *
     * @param medicamentId type is {@link Long}.
     *                     <code>medicamentId</code> which checked quantity.
     * @param count        type is {@link Integer}.
     *                     <code>count</code> is transmitted value for checking.
     * @return <code>true</code> if medicines's quantity more than <code>count</code> else returns <code>false</code>.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    boolean checkQuantityInStock(Long medicamentId, Integer count) throws DaoException;

    /**
     * The method checks available recipe {@link com.epam.finalproject.pharmacy.entity.Recipe}
     * for user, medicament with quantity.
     *
     * @param userId            type is {@link Long}.
     *                          <code>userId</code> is user's id that contains checked recipe.
     * @param medicamentId      type is {@link Long}.
     *                          A medicament with <code>medicamentId</code> the recipe of which checking.
     * @param tempTotalQuantity type {@link Integer}.
     *                          <code>tempTotalQuantity</code> is value which is compared with
     *                          available quantity of medicament in the database.
     * @return <code>true</code> if in database was found available recipe.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    boolean checkAvailableRecipe(Long userId, Long medicamentId, Integer tempTotalQuantity) throws DaoException;

    /**
     * The method change status "archive" {@link Medicament}.
     *
     * @param id type is {@link Long}.
     *           <code>id</code> of medicament which status "archive" need to change.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    void sendMedicamentToArchive(Long id) throws DaoException;

    /**
     * The method finds all medicines with recipe {@link com.epam.finalproject.pharmacy.entity.Recipe}.
     *
     * @return {@link List} with objects {@link Medicament}.
     * If objects {@link Medicament} weren't found the list will be empty.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<Medicament> findAllMedicinesWithRecipe() throws DaoException;

    /**
     * The method returns available medicines in a range.
     *
     * @param startRow type is "int". <code>startRow</code> is first row for finding.
     * @param count    type is "int". <code>count</code> rows find in the database for
     *                 the requested page.
     * @return {@link List} with objects {@link Medicament}.
     * If objects {@link Medicament} weren't found in the range the list will be empty.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<Medicament> findAllAvailableMedicamentForRequestedPage(int startRow, int count) throws DaoException;

    /**
     * The method returns list of available medicines.
     *
     * @return {@link List} with objects {@link Medicament}.
     * If objects {@link Medicament} weren't found in the range the list will be empty.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<Medicament> getAllAvailableMedicines() throws DaoException;
}
