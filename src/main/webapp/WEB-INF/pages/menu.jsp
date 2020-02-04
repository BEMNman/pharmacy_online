<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 24.01.2020
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<style type="text/css">
    <%@include file="/resources/css/menu.css" %>
</style>

<html>
<head>
    <div class="link-menu">
        <p>
            <a href="controller?command=mainPage">
                <fmt:message key="medicines" bundle="${rb}"/>
            </a>
        </p>
        <c:if test="${sessionScope.user.role.name() =='PATIENT'}">
            <p>
                <a href="controller?command=openOrders">
                    <fmt:message key="orders" bundle="${rb}"/>
                </a>
            </p>
        </c:if>
        <c:if test="${sessionScope.user.role.name() =='PATIENT' || sessionScope.user.role.name() == 'DOCTOR'}">
            <p>
                <a href="controller?command=openRecipes">
                    <fmt:message key="recipes" bundle="${rb}"/>

                </a>
            </p>
        </c:if>
        <c:if test="${sessionScope.user.role.name() =='DOCTOR'}">
            <p>
                <a href="controller?command=openRequests">
                    <fmt:message key="requests" bundle="${rb}"/>
                </a>
            </p>
        </c:if>
    </div>
</head>
<body>

</body>
</html>
