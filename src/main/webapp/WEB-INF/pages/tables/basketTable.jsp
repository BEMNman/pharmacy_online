<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 15.01.2020
  Time: 1:17
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
    <h2><fmt:message key="header.basket" bundle="${rb}"/></h2>
</div>
<c:if test="${sessionScope.medicinesInBasket.size() == null}">
    <span>
        <fmt:message key="basket.empty" bundle="${rb}"/>
        <fmt:message key="basket.click" bundle="${rb}"/>
        <a href="controller?command=mainPage">
                    <fmt:message key="basket.here" bundle="${rb}"/>
        </a>
                <fmt:message key="basket.continue" bundle="${rb}"/>
    </span>
</c:if>
<c:if test="${sessionScope.medicinesInBasket.size() >= 1}">
    <form id="checkBasket" method="get">
        <table>
            <tr>
                <th>
                    <fmt:message key="medicines.name" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.form" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.dosage" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.recipe" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.in_stock" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.price" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.quantity" bundle="${rb}"/>
                </th>
                <th></th>
            </tr>
            <tbody>

            <tbody>
            <c:forEach items="${sessionScope.medicinesInBasket.keySet()}" var="medicament">
                <tr>
                    <td><c:out value="${medicament.name}"/></td>
                    <td><c:out value="${medicament.form}"/></td>
                    <td><c:out value="${medicament.dosage}"/></td>
                    <td>
                        <c:if test="${medicament.recipe}">
                            <fmt:message key="yes" bundle="${rb}"/>
                        </c:if>
                        <c:if test="${!medicament.recipe}">
                            <fmt:message key="no" bundle="${rb}"/>
                        </c:if>
                    </td>
                    <td><c:out value="${medicament.quantity}"/></td>
                    <td><c:out value="${medicament.price}"/></td>
                    <td>
                        <input name="id" hidden value="${medicament.id}">
                        <input name="count"
                               type="number"
                               min="0"
                               max="${medicament.quantity}"
                               value="${sessionScope.medicinesInBasket.get(medicament)}"
                               placeholder="${sessionScope.medicinesInBasket.get(medicament)}"
                               style="width: 4em"/>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <c:if test="${requestScope.totalPrice != null}">
            <h3>
                <fmt:message key="basket.total_price" bundle="${rb}"/>
                    ${requestScope.totalPrice}
            </h3>
            <a href="controller?command=pay">
                <fmt:message key="basket.pay" bundle="${rb}"/>
            </a>
        </c:if>

        <input type="hidden" name="command" value="continueOrder"/>
        <input type="submit" value=" <fmt:message key="order.check_order" bundle="${rb}"/>"/>
    </form>

    <c:if test="${requestScope.totalPrice == null}">
        <a href="controller?command=mainPage">
            <fmt:message key="basket.return" bundle="${rb}"/>
        </a>
        <a href="controller?command=clearBasket">
            <fmt:message key="basket.clear_basket" bundle="${rb}"/>
        </a>
    </c:if>
</c:if>
</body>
</html>
