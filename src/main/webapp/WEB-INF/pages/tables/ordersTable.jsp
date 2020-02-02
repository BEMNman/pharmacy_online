<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 16.01.2020
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<body>

<div style="border: 1px black">
    <h2>
        <fmt:message key="orders" bundle="${rb}"/>
    </h2>
</div>
<table>
    <tr>
        <th>
            <fmt:message key="date" bundle="${rb}"/>
        </th>
        <th>
            <fmt:message key="order.price" bundle="${rb}"/>
        </th>
        <th>
            <fmt:message key="order.status" bundle="${rb}"/>
        </th>
        <th></th>
    </tr>

    <tbody>
    <c:forEach items="${requestScope.orders}" var="order">
        <tr>
            <td><c:out value="${order.creationDate}"/></td>
            <td><c:out value="${order.price}"/></td>
            <td><c:out value="${order.status}"/></td>

            <td align="centre">
                <p>
                    <a href="controller?command=viewOrderDetails&orderId=${order.id}">
                        <fmt:message key="order.view" bundle="${rb}"/>
                    </a>
                </p>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
