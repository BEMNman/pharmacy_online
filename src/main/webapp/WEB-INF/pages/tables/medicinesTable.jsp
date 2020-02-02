<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="locale" var="rb" />

<html>

<body>

<div style="border: 1px black">
    <h2>
        <fmt:message key="medicines" bundle="${rb}"/>
    </h2>
</div>
<table style=" border: 2px solid; width: auto; text-align:center">
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
            <fmt:message key="medicines.amount_in_pack" bundle="${rb}"/>
        </th>
        <th>
            <fmt:message key="medicines.price" bundle="${rb}"/>
        </th>
        <th>
            <fmt:message key="medicines.quantity" bundle="${rb}"/>
        </th>
        <th></th>
    </tr>

    <tbody class="w3-centered" bgcolor="white">
    <c:forEach items="${requestScope.medicines}" var="medicament">
            <tr>
                <td><c:out value="${medicament.name}"/></td>
                <td><c:out value="${medicament.form.name().toLowerCase()}"/></td>
                <td><c:out value="${medicament.dosage}"/></td>
                <td>
                    <c:if test="${medicament.recipe}">
                        <fmt:message key="yes" bundle="${rb}"/>
                    </c:if>
                    <c:if test="${!medicament.recipe}">
                        <fmt:message key="no" bundle="${rb}"/>
                    </c:if>
                </td>
                <td><c:out value="${medicament.amountInPack}"/></td>
                <td><c:out value="${medicament.price}"/></td>
                <td><c:out value="${medicament.quantity}"/></td>
                <c:if test="${sessionScope.user.role=='PATIENT'}">
                    <td>
                        <form action="addMedicamentInBasket" method="post"
                              style="display: inline-block; margin: 0;">
                            <input type="hidden" name="command" value="addMedicamentInBasket"/>
                            <input type="hidden" name="medicamentId" value="${medicament.id}">
                            <input name="count"
                                   type="number"
                                   min="1"
                                   max="${medicament.quantity}"
                                   value="1"
                                   placeholder="${1}"
                                   style="width: 4em"/>
                            <input type="submit"
                                   value="<fmt:message key="add" bundle="${rb}"/>"/>
                        </form>
                    </td>
                </c:if>
                <c:if test="${sessionScope.user.role=='PHARMACIST'}">
                    <td>
                        <form action="editMedicament" method="post"
                              style="display: inline-block; margin: 0;">
                            <input type="hidden" name="command" value="editMedicament"/>
                            <input type="hidden" name="medicamentId" value="${medicament.id}">
                            <input type="submit" value="<fmt:message key="edit" bundle="${rb}"/>"/>
                        </form>
                    </td>
                    <td>
                        <form action="deleteMedicament" method="post"
                              style="display: inline-block; margin: 0;">
                            <input type="hidden" name="command" value="deleteMedicament"/>
                            <input type="hidden" name="medicamentId" value="${medicament.id}">
                            <input type="submit" value="<fmt:message key="delete" bundle="${rb}"/>"/>
                        </form>
                    </td>
                </c:if>
            </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
