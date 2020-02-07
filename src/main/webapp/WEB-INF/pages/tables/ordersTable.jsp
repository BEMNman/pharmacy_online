<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<div class="main">
    <h1>
        <fmt:message key="orders" bundle="${rb}"/>
    </h1>
    <table class="bordered">
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
            <th>
                <fmt:message key="request.action" bundle="${rb}"/>
            </th>
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
                    <form action="viewOrderDetails" method="get"
                          style="display: inline-block; margin: 0;">
                        <input type="hidden" name="command" value="viewOrderDetails"/>
                        <input type="hidden" name="orderId" value="${order.id}">
                        <input id="submit" type="submit" value="<fmt:message key="order.view" bundle="${rb}"/>"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
