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
<html>
<body>

<div style="border: 1px black">
    <h2>Orders</h2>
</div>
<table>
    <tr>
        <th>Date</th>
        <th>Price</th>
        <th>Status</th>
        <th></th>
    </tr>

    <tbody>
    <c:forEach items="${requestScope.orders}" var="order">
        <tr>
            <td><c:out value="${order.creationDate}"/></td>
            <td><c:out value="${order.price}"/></td>
            <td><c:out value="${order.status}"/></td>

            <td align="centre">
                <p><a href="controller?command=viewOrderDetails&orderId=${order.id}">open</a></p>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
