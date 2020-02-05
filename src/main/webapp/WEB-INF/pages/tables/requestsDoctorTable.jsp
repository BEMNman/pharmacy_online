<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<div class="main">
    <h1><fmt:message key="requests" bundle="${rb}"/></h1>
    <table class="bordered">
        <tr>
            <th>
                <fmt:message key="creation_date" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="order.status" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="medicines.medicament" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="medicines.dosage" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="medicines.quantity" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="recipe.patient" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="exp_date" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="request.period" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="request.action" bundle="${rb}"/>
            </th>
        </tr>
        <tbody>
        <c:forEach items="${requestScope.requestsDto}" var="request">
            <tr>
                <td><c:out value="${request.creationDate}"/></td>
                <td>
                    <c:if test="${request.status == 'NEW'}">
                        <fmt:message key="status.new" bundle="${rb}"/>
                    </c:if>
                    <c:if test="${request.status == 'CLOSED'}">
                        <fmt:message key="status.closed" bundle="${rb}"/>
                    </c:if>
                </td>
                <td><c:out value="${request.medicamentName}"/></td>
                <td><c:out value="${request.medicamentDosage}"/></td>
                <td><c:out value="${request.quantity}"/></td>
                <td><c:out value="${request.patientName}"/></td>
                <td><c:out value="${request.expDate}"/></td>
                <td><c:out value="${request.requestedPeriod}"/></td>
                <td>
                    <c:if test="${request.status == 'NEW'}">
                        <form action="approveRequest" method="post"
                              style="display: inline-block; margin: 0;">
                            <input type="hidden" name="command" value="approveRequest"/>
                            <input type="hidden" name="requestId" value="${request.id}"/>
                            <input type="submit" value="<fmt:message key="button.approve" bundle="${rb}"/>"/>
                        </form>
                        <form action="rejectRequest" method="post"
                              style="display: inline-block; margin: 0;">
                            <input type="hidden" name="command" value="rejectRequest"/>
                            <input type="hidden" name="requestId" value="${request.id}"/>
                            <input type="submit" value="<fmt:message key="button.reject" bundle="${rb}"/>"/>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
