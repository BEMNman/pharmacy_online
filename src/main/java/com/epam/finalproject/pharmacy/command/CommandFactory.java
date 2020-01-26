package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.command.patient.*;
import com.epam.finalproject.pharmacy.command.pharmacist.DeleteMedicamentCommand;
import com.epam.finalproject.pharmacy.command.pharmacist.EditMedicamentCommand;
import com.epam.finalproject.pharmacy.command.util.Calculator;
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

            case "registerNewPatient":
                logger.debug("Command 'registerNewPatient' was created");
                return new ShowPageCommand("registerNewPatient.jsp");

            case "saveNewPatient":
                logger.debug("Command 'saveNewPatient' was created");
                return new RegisterNewPatientCommand(new UserService(new DaoHelperFactory()));

            case "openBasket":
                logger.debug("Command 'openBasket' was created");
                return new OpenBasketCommand();

            case "patientMain":
                logger.debug("Command 'patientMain' was created");
                return new PatientMainCommand(new MedicamentService(new DaoHelperFactory()));

            case "addMedicamentInBasket":
                logger.debug("Command 'addMedicamentInBasket' was created");
                return new AddMedicamentInBasketCommand(new MedicamentService(new DaoHelperFactory()));

            case "saveOrder":
                logger.debug("Command 'saveOrder' was created");
                return new SaveOrderCommand(new OrderService(new DaoHelperFactory()));

            case "openOrders":
                logger.debug("Command 'openOrders' was created");
                return new ShowOrderDetailsCommand(new OrderService(new DaoHelperFactory()));

            case "viewOrderDetails":
                logger.debug("Command 'viewOrderDetails' was created");
                return new ViewOrderDetailsCommand(new MedicamentService(new DaoHelperFactory()));

            case "openRecipes":
                logger.debug("Command 'openRecipes' was created");
                return new ShowRecipesCommand(new RecipeDtoService(new DaoHelperFactory()));

            case "continueOrder":
                logger.debug("Command 'continueOrder' was created");
                return new CheckOrderCommand(new MedicamentService(new DaoHelperFactory()));

            case "openOrder":
                logger.debug("Command 'openOrder' was created");
                return new OpenOrderCommand(new Calculator());

            case "clearBasket":
                logger.debug("Command 'openOrder' was created");
                return new ClearBasketCommand();

            case "pay":
                logger.debug("Command 'payOrder' was created");
                return new PayOrderCommand(new OrderService(new DaoHelperFactory()));

            case "sendRecipeRequest":
                logger.debug("Command 'sendRequestRecipe' was created");
                return new SendRecipeRequestCommand(new RequestService(new DaoHelperFactory()));

            case "deleteMedicament":
                logger.debug("Command 'deleteMedicament' was created");
                return new DeleteMedicamentCommand(new MedicamentService(new DaoHelperFactory()));

            case "editMedicament":
                logger.debug("Command 'editMedicament' was created");
                return new EditMedicamentCommand(new MedicamentService(new DaoHelperFactory()));

            default:
                logger.error("Unknown command: " + command);
                throw new IllegalArgumentException("Unknown command " + command);
        }
    }
}
