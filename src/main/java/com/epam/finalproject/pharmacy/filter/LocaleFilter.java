package com.epam.finalproject.pharmacy.filter;

import com.epam.finalproject.pharmacy.command.constant.RequestParameterConst;
import com.epam.finalproject.pharmacy.command.constant.SessionAttributeConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {

    private static Locale DEFAULT_LOCALE_LANG= Locale.getDefault();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute(SessionAttributeConst.LOCALE_LANG) == null) {
            session.setAttribute(SessionAttributeConst.LOCALE_LANG, DEFAULT_LOCALE_LANG);
        }
        String localeName = req.getParameter(RequestParameterConst.LANGUAGE);

        System.out.println("localeName = " + localeName);

        if(localeName != null) {
            Locale locale;
            switch (localeName) {
                case "ru":
                    locale =  new Locale("ru","RU");
                    break;
                case "be":
                    locale =  new Locale("be","BY");
                    break;
                case "en":
                    locale = new Locale("en","EN");
                    break;
                default: locale = DEFAULT_LOCALE_LANG;
            }
            session.setAttribute(SessionAttributeConst.LOCALE_LANG, locale);
        }
        chain.doFilter(req, resp);
    }
}
