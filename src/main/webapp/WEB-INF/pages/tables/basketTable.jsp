<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<script type="text/javascript">
    <%@include file="/resources/js/inputValidatorCreateEdit.js"%>
</script>

<div class="main">
    <h1>
        <fmt:message key="header.basket" bundle="${rb}"/>
    </h1>
    <c:if test="${sessionScope.medicinesInBasket.size() == null}">
        <div class="main">
            <div class="mobile">
                <div class="form">
                    <h2>
                        <fmt:message key="basket.empty" bundle="${rb}"/>
                        <fmt:message key="basket.click" bundle="${rb}"/>
                        <a href="$${pageContext.request.contextPath}controller?command=mainPage">
                            <fmt:message key="basket.here" bundle="${rb}"/>
                        </a>
                        <fmt:message key="basket.continue" bundle="${rb}"/>
                    </h2>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${sessionScope.medicinesInBasket.size() >= 1}">
        <form id="checkBasket" method="get">
            <table class="bordered">
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
                </tr>
                <tbody>
                <c:forEach items="${sessionScope.medicinesInBasket.keySet()}" var="medicament">
                    <tr>
                        <td><c:out value="${medicament.name}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${medicament.form.name()=='PILL'}">
                                    <fmt:message key="medicines.pill" bundle="${rb}"/>
                                </c:when>
                                <c:when test="${medicament.form.name()=='SOLUTION'}">
                                    <fmt:message key="medicines.solution" bundle="${rb}"/>
                                </c:when>
                                <c:when test="${medicament.form.name()=='POWDER'}">
                                    <fmt:message key="medicines.powder" bundle="${rb}"/>
                                </c:when>
                                <c:when test="${medicament.form.name()=='CREAM'}">
                                    <fmt:message key="medicines.cream" bundle="${rb}"/>
                                </c:when>
                                <c:when test="${medicament.form.name()=='GEL'}">
                                    <fmt:message key="medicines.gel" bundle="${rb}"/>
                                </c:when>
                            </c:choose>
                        </td>
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
                            <input type="hidden" name="id" value="${medicament.id}">
                            <input name="count" id="input-quantity"
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
                <a href="${pageContext.request.contextPath}?command=pay">
                    <fmt:message key="basket.pay" bundle="${rb}"/>
                </a>
            </c:if>

            <input type="hidden" name="command" value="continueOrder"/>

            <button href="${pageContext.request.contextPath}controller?command=continueOrder">
                <fmt:message key="order.order" bundle="${rb}"/>
            </button>
        </form>
        <div class="button-group">
            <c:if test="${requestScope.totalPrice == null}">

                <a style="margin: 20px" href="${pageContext.request.contextPath}controller?command=mainPage">
                    <fmt:message key="return" bundle="${rb}"/>
                </a>

                <a style="margin: 20px" href="${pageContext.request.contextPath}controller?command=clearBasket">
                    <fmt:message key="basket.clear_basket" bundle="${rb}"/>
                </a>
            </c:if>
        </div>
    </c:if>
</div>
