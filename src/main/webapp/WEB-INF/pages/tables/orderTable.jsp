<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<style type="text/css">
    <%@include file="/resources/css/style.css"%>
</style>

<c:if test="${requestScope.messageToPage != null}">
    <jsp:include page="../message.jsp"/>
</c:if>
<c:if test="${requestScope.messageToPage == null}">
    <h2>
        <fmt:message key="order.order" bundle="${rb}"/>
    </h2>
    <div class="order-wrapper">
        <div class="order-form">
            <div class="payment" style="float: left">
                <div class="form">
                    <form action="pay" method="get">
                        <div class="row">
                            <div class="col-50">
                                <h3>
                                    <fmt:message key="order.payment" bundle="${rb}"/>
                                </h3>
                                <label for="cname">
                                    <fmt:message key="order.name_on_card" bundle="${rb}"/>
                                </label>
                                <input type="text" id="cname" name="cardname"
                                       placeholder="<fmt:message key="order.placeholder.name_surname" bundle="${rb}"/>"
                                       required
                                       pattern="[A-Z]+ [A-Z]+">
                                <label for="ccnum">
                                    <fmt:message key="order.credit_card_number" bundle="${rb}"/>
                                </label>
                                <input type="text" id="ccnum" name="cardnumber"
                                       placeholder="<fmt:message key="order.placeholder.cart_number" bundle="${rb}"/>"
                                       required
                                       pattern="[0-9]{4}( -)?[0-9]{4}( -)?[0-9]{4}( -)?[0-9]{4}"
                                       maxlength="16">
                                <label for="expdate"><fmt:message key="exp_date" bundle="${rb}"/></label>
                                <input type="text" id="expdate" name="expdate"
                                       placeholder="<fmt:message key="order.placeholder.exp_date" bundle="${rb}"/>"
                                       required
                                       pattern="[0-9]{2}/[0-9]{2}">
                                <div class="row">
                                    <div class="col-50">
                                        <label for="cvv">CVV</label>
                                        <input type="text" id="cvv" required="required" name="cvv" placeholder="543"
                                               pattern="[0-9]{3}" maxlength="3">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="command" value="pay">
                        <button type="submit"><fmt:message key="order.continue_to_checkout" bundle="${rb}"/></button>
                    </form>
                </div>
            </div>
            <div class="bill" style="float: right">
                <div class="form">
                    <table class="bordered-order">
                        <tbody>
                        <c:forEach items="${sessionScope.medicinesInBasket.keySet()}" var="medicament">
                            <tr>
                                <td>
                                        ${medicament.name}
                                </td>
                                <td>
                                        ${sessionScope.medicinesInBasket.get(medicament)}
                                </td>
                                <td>
                                        ${sessionScope.medicinesInBasket.get(medicament)*medicament.price}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <hr>
                    <p>
                        <fmt:message key="basket.total_price" bundle="${rb}"/>
                        <span class="price"
                              style="color:black">$${requestScope.totalPrice}</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</c:if>

