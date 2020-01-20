package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {

    private static final Logger logger = LogManager.getLogger(CommandFactory.class.getName());

    public static Command create(String command) throws ServerException {
        switch (command) {
            case "login":
                logger.debug("Command 'login' was created");
                return new LoginCommand(new UserService(new DaoHelperFactory()));

            case "authorization":
                logger.debug("Command 'mainPage' was created");
                return new Authorization();

            case "logout":
                logger.debug("Command 'logout' was created");
                return new LogoutCommand();

            case "registerNewPacient":
                logger.debug("Command 'registerNewPacient' was created");
                return new ShowPageCommand("registerNewPacient.jsp");

            case "saveNewPacient":
                logger.debug("Command 'saveNewPacient' was created");
                return new RegisterNewPacientCommand(new UserService(new DaoHelperFactory()));

            case "openBasket":
                logger.debug("Command 'openBasket' was created");
                return new OpenBasketCommand(new MedicamentService(new DaoHelperFactory()));

            case "pacientMain":
                logger.debug("Command 'pacientMain' was created");
                return new PacientMainCommand(new MedicamentService(new DaoHelperFactory()));

            case "addMedicamentInBasket":
                logger.debug("Command 'addMedicamentInBasket' was created");
                return new AddMedicamentInBasketCommand();

            case "saveOrder":
                logger.debug("Command 'saveOrder' was created");
                return new SaveOrderCommand(new OrderService(new DaoHelperFactory())
                        , new OrderDetailsService(new DaoHelperFactory()));

            case "openOrders":
                logger.debug("Command 'openOrders' was created");
                return new OpenOrderCommand(new OrderService(new DaoHelperFactory()));

            case "viewOrderDetails":
                logger.debug("Command 'viewOrderDetails' was created");
                return new ViewOrderDetailsCommand(new MedicamentService(new DaoHelperFactory()));

            case "openRecipes":
                logger.debug("Command 'openRecipes' was created");
                return new ShowRecipesCommand(new RecipeDtoService(new DaoHelperFactory()));

            case "payOrder":
                logger.debug("Command 'payOrder' was created");
                return new PayOrderCommand(new OrderService(new DaoHelperFactory()),
                        new MedicamentService(new DaoHelperFactory()));

            default:
                logger.error("Unknown command: " + command);
                throw new IllegalArgumentException("Unknown command " + command);
        }
    }
}
