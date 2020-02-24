<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/logo_pharmacy.jpg"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:setBundle basename="locale" var="rb"/>

</head>

<div class="main">
    <div class="mobile">
        <div class="form-infoMessage">
            <h2 class="infoMessage"><fmt:message key="${requestScope.messageToPage}" bundle="${rb}"/></h2>
            <div class="button-group">
                <c:if test="${requestScope.messageToPage.contains('invalid_credit_cart_dat')
                || requestScope.messageToPage.contains('message.entered_quantity_recipe')}">
                    <a href="${pageContext.request.contextPath}controller?command=openBasket">
                        <fmt:message key="return" bundle="${rb}"/>
                    </a>
                </c:if>
                <c:if test="${!requestScope.messageToPage.contains('invalid_credit_cart_dat')
                && !requestScope.messageToPage.contains('message.entered_quantity_recipe')}">
                    <a href="${pageContext.request.contextPath}controller?command=mainPage&page=${requestScope.page}">
                        <fmt:message key="return" bundle="${rb}"/>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>
