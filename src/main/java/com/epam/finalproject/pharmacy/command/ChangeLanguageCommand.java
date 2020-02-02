package com.epam.finalproject.pharmacy.command;

import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;
import com.epam.finalproject.pharmacy.exception.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLanguageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServerException {
        String currentUrlForRedirect = "redirect:" + request.getHeader("referer");
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttributeConst.LOCALE_LANG) == null) {
            session.setAttribute(SessionAttributeConst.LOCALE_LANG, Locale.getDefault());
        }

        String localeName = request.getParameter(RequestParameterConst.LANGUAGE);
        if (localeName != null) {
            Locale locale;
            switch (localeName) {
                case "ru":
                    locale = new Locale("ru", "RU");
                    break;
                case "be":
                    locale = new Locale("be", "BY");
                    break;
                case "en":
                    locale = new Locale("en", "EN");
                    break;
                default:
                    locale = Locale.getDefault();
            }
            session.setAttribute(SessionAttributeConst.LOCALE_LANG, locale);
        }
        return CommandResult.redirect(currentUrlForRedirect);
    }

}
