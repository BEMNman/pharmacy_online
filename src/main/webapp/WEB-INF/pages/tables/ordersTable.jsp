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

<style>
    <%@include file="/resources/css/styles.css"%>
</style>

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
            <td>
                <fmt:parseDate value="${ order.creationDate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="creationDate"
                               type="both"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${creationDate}"/>
            </td>
            <td><c:out value="${order.price}"/></td>
            <td>
                <c:if test="${order.status == 'PAID'}">
                    <fmt:message key="status.paid" bundle="${rb}"/>
                </c:if>
                <c:if test="${order.status == 'CANCELED'}">
                    <fmt:message key="status.canceled" bundle="${rb}"/>
                </c:if>
                <c:if test="${order.status == 'PROCESS'}">
                    <fmt:message key="status.process" bundle="${rb}"/>
                </c:if>
            </td>

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
