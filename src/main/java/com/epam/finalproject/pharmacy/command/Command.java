package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code Command} is interface.
 *
 * <p> Contains a
 * single method {@code execute}.
 *
 * @author Gogolinsky
 * @see com.epam.finalproject.pharmacy.entity.Request
 */

public interface Command {

    /**
     * The method should return the result of the command.
     * <p> The method takes a single argument <code>request</code> ({@link HttpServletRequest}).
     *
     * @param request an object that was received when processing a client request.
     * @return CommandResult an object that contains a page to which the user must be redirected.
     * @throws ServerException the method throws an exception if the service method throws an exception
     *                         or there are invalid values in the <code>request</code>
     * @see HttpServletRequest
     */
    CommandResult execute(HttpServletRequest request) throws ServerException;
}
