<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<html>
<body>
<div style="border: 1px black">
    <h2>
        <fmt:message key="order.details_of_order" bundle="${rb}"/>
    </h2>
</div>
    <table style="border: 2px solid; width: 600px; text-align:center;">
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
                <fmt:message key="medicines.amount_in_pack" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="medicines.recipe" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="medicines.quantity" bundle="${rb}"/>
            </th>
            <th>
                <fmt:message key="medicines.price" bundle="${rb}"/>
            </th>
        </tr>

        <tbody class="w3-centered" bgcolor="white">
        <c:forEach items="${requestScope.medicinesInOrder}" var="medicament">
            <tr>
                <td height="30px" align="left"><c:out value="${medicament.name}"/></td>
                <td height="30px" align="left"><c:out value="${medicament.form}"/></td>
                <td height="30px" align="left"><c:out value="${medicament.dosage}"/></td>
                <td height="30px" align="left"><c:out value="${medicament.amountInPack}"/></td>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
