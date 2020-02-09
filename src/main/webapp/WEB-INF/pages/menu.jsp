<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<div class="leftCol">
    <ul class="leftNav">
        <li>
            <a href="${pageContext.request.contextPath}controller?command=mainPage">
                <fmt:message key="medicines" bundle="${rb}"/>
            </a>
        </li>
        <c:if test="${sessionScope.user.role.name() =='PATIENT'}">
            <li>
                <a href="${pageContext.request.contextPath}controller?command=openOrders">
                    <fmt:message key="orders" bundle="${rb}"/>
                </a>
            </li>
        </c:if>
        <c:if test="${sessionScope.user.role.name() =='PATIENT' || sessionScope.user.role.name() == 'DOCTOR'}">
            <li>
                <a href="${pageContext.request.contextPath}controller?command=openRecipes">
                    <fmt:message key="recipes" bundle="${rb}"/>
                </a>
            </li>
        </c:if>
        <c:if test="${sessionScope.user.role.name() =='DOCTOR'}">
            <li>
                <a href="${pageContext.request.contextPath}controller?command=openRequests">
                    <fmt:message key="recipe_extension" bundle="${rb}"/>
                </a>
            </li>
        </c:if>
    </ul>
</div>

