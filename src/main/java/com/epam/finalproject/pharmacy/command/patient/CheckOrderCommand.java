package com.epam.finalproject.pharmacy.command.patient;

        import com.epam.finalproject.pharmacy.command.Command;
        import com.epam.finalproject.pharmacy.command.CommandResult;
        import com.epam.finalproject.pharmacy.command.constant.Page;
        import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
        import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
        import com.epam.finalproject.pharmacy.entity.Medicament;
        import com.epam.finalproject.pharmacy.entity.User;
        import com.epam.finalproject.pharmacy.exception.ServerException;
        import com.epam.finalproject.pharmacy.service.MedicamentService;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;
        import java.util.Map;

public class CheckOrderCommand implements Command {

    private MedicamentService service;

    public CheckOrderCommand(MedicamentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeConst.USER);
        String[] count = request.getParameterValues(RequestParameterConst.COUNT_MEDICAMENT);
        Map<Medicament, Integer> medicinesAndCountInBasket =
                (Map) session.getAttribute(SessionAttributeConst.MEDICINES_IN_BASKET);
        String message = service.checkOrder(user, medicinesAndCountInBasket, count);
        if(message.isEmpty()) {
            return CommandResult.redirectToCommand("openOrder");
        }
        request.setAttribute(RequestParameterConst.MESSAGE_TO_JSP, message);
        return CommandResult.forward(Page.PATIENT_ORDER_DETAILS);
    }
}
