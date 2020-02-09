package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.command.constant.Page;
import com.epam.finalproject.pharmacy.command.doctor.*;
import com.epam.finalproject.pharmacy.command.patient.*;
import com.epam.finalproject.pharmacy.command.pharmacist.DeleteMedicamentCommand;
import com.epam.finalproject.pharmacy.command.pharmacist.EditMedicamentCommand;
import com.epam.finalproject.pharmacy.command.pharmacist.SaveMedicamentCommand;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.exception.ServerException;
import com.epam.finalproject.pharmacy.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {

    private static final Logger logger = LogManager.getLogger(CommandFactory.class.getName());

    public static final String SHOW_ERROR_PAGE = "errorPage";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String REGISTER_NEW_PATIENT = "registerNewPatient";
    public static final String SAVE_NEW_PATIENT = "saveNewPatient";

    public static final String OPEN_BASKET = "openBasket";
    public static final String MAIN_PAGE = "mainPage";
    public static final String ADD_MEDICAMENT_IN_BASKET = "addMedicamentInBasket";
    public static final String SAVE_ORDER = "saveOrder";
    public static final String OPEN_ORDERS = "openOrders";
    public static final String VIEW_ORDER_DETAILS = "viewOrderDetails";
    public static final String OPEN_RECIPES = "openRecipes";
    public static final String CONTINUE_ORDER = "continueOrder";
    public static final String OPEN_ORDER = "openOrder";
    public static final String CLEAR_BASKET = "clearBasket";
    public static final String PAY = "pay";
    public static final String SEND_RECIPE_REQUEST = "sendRecipeRequest";

    public static final String DELETE_MEDICAMENT = "deleteMedicament";
    public static final String EDIT_MEDICAMENT = "editMedicament";
    public static final String SAVE_MEDICAMENT = "saveMedicament";
    public static final String OPEN_CREATION_FORM_MEDICAMENT = "openCreationFormMedicament";

    public static final String OPEN_CREATION_FORM_RECIPE = "openCreationFormRecipe";
    public static final String SAVE_RECIPE = "saveRecipe";
    public static final String OPEN_REQUESTS = "openRequests";
    public static final String REJECT_REQUEST = "rejectRequest";
    public static final String APPROVE_REQUEST = "approveRequest";

    public static final String SWITCH_LOCALE = "switchLocale";


    public static Command create(String command) throws ServerException {

        logger.debug("Command " + command + " was created");

        switch (command) {
            case SHOW_ERROR_PAGE:
                return new ShowPageCommand(Page.ERROR);
            case LOGIN:
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case LOGOUT:
                return new LogoutCommand();
            case REGISTER_NEW_PATIENT:
                return new ShowPageCommand(Page.REGISTRATION_NEW_PATIENT);
            case SAVE_NEW_PATIENT:
                return new RegisterNewPatientCommand(new UserService(new DaoHelperFactory()));
            case OPEN_BASKET:
                return new OpenBasketCommand();
            case MAIN_PAGE:
                return new ShowMainPageCommand(new MedicamentService(new DaoHelperFactory()));
            case ADD_MEDICAMENT_IN_BASKET:
                return new AddMedicamentInBasketCommand(new MedicamentService(new DaoHelperFactory()));
            case SAVE_ORDER:
                return new SaveOrderCommand(new OrderService(new DaoHelperFactory()));
            case OPEN_ORDERS:
                return new ShowOrderDetailsCommand(new OrderService(new DaoHelperFactory()));
            case VIEW_ORDER_DETAILS:
                return new ViewOrderDetailsCommand(new MedicamentService(new DaoHelperFactory()));
            case OPEN_RECIPES:
                return new ShowRecipesCommand(new RecipeDtoService(new DaoHelperFactory()));
            case CONTINUE_ORDER:
                return new CheckOrderCommand(new MedicamentService(new DaoHelperFactory()));
            case OPEN_ORDER:
                return new OpenOrderCommand();
            case CLEAR_BASKET:
                return new ClearBasketCommand();
            case PAY:
                return new PayOrderCommand(new OrderService(new DaoHelperFactory()));
            case SEND_RECIPE_REQUEST:
                return new SendRecipeRequestCommand(new RequestService(new DaoHelperFactory()));
            case DELETE_MEDICAMENT:
                return new DeleteMedicamentCommand(new MedicamentService(new DaoHelperFactory()));
            case EDIT_MEDICAMENT:
                return new EditMedicamentCommand(new MedicamentService(new DaoHelperFactory()));
            case SAVE_MEDICAMENT:
                return new SaveMedicamentCommand(new MedicamentService(new DaoHelperFactory()));
            case OPEN_CREATION_FORM_MEDICAMENT:
                return new ShowPageCommand(Page.PHARMACIST_EDIT_CREATE_MEDICAMENT);
            case OPEN_CREATION_FORM_RECIPE:
                return new OpenCreationRecipeFormCommand(new CreationRecipeService(new DaoHelperFactory()));
            case SAVE_RECIPE:
                return new SaveRecipeCommand(new RecipeService(new DaoHelperFactory()));
            case OPEN_REQUESTS:
                return new OpenRequestsCommand(new RequestDtoService(new DaoHelperFactory()));
            case REJECT_REQUEST:
                return new RejectRequestCommand(new RequestService(new DaoHelperFactory()));
            case APPROVE_REQUEST:
                return new ApproveRequestCommand(new ExtensionRecipeService(new DaoHelperFactory()));
            default:
                logger.error("Unknown command: " + command);
                throw new IllegalArgumentException("Unknown command " + command);
        }
    }
}
