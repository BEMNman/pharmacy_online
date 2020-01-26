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
<html>
<head>
    <p><a href="controller?command=patientMain">Medicines</a></p>
    <c:if test="${sessionScope.user.role.name() =='PATIENT'}">
        <p><a href="controller?command=openOrders">Orders</a></p>
    </c:if>
    <c:if test="${sessionScope.user.role.name() =='PATIENT' || sessionScope.user.role.name() == 'DOCTOR'}">
        <p><a href="controller?command=openRecipes">Recipes</a></p>
    </c:if>
    <c:if test="${sessionScope.user.role.name() =='DOCTOR'}">
        <p><a href="XXXXXXXXXXXl">Request</a></p>
    </c:if>
</head>
<body>

</body>
</html>
