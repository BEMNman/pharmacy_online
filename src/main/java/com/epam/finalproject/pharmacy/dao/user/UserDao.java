package com.epam.finalproject.pharmacy.dao.user;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.entity.UserRole;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface extends an {@link Dao}.
 * The interface contains description the general methods for
 * working with the database for objects {@link User}.
 *
 * @author Gogolinsky
 * @see Dao
 * @see User
 */

public interface UserDao extends Dao<User> {

    /**
     * The method checks that <code>user</code> with <code>login</code>
     * and <code>password</code> exists in the database.
     *
     * @param login    type is {@link String}.
     *                 <code>login</code> which user uses for login, unique for all users.
     * @param password type is {@link String}.
     *                 <code>password</code> which user uses to log in.
     * @return {@link Optional} which contains an object with <code>login</code>
     * and <code>password</code> found in the database.
     * If that object wasn't found in database optional is empty.
     * If <code>user</code> was found with <code>login</code>
     * and <code>password</code> {@link Optional} contains object {@link User}.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * The method finds all users in the database by <code>role</code>.
     *
     * @param role type is {@link UserRole}.
     *             <code>role</code> for the user determines access to functions in the application.
     * @return {@link List} with {@link User} who have <code>role</code>.
     * If users weren't found, the list will be empty.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<User> findAllUserByRole(UserRole role) throws DaoException;
}
