<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 20.01.2020
  Time: 1:05
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
        <fmt:message key="recipes" bundle="${rb}"/>
    </h2>
</div>
<c:if test="${sessionScope.user.role == 'DOCTOR'}">
    <form name="create" action="openCreationFormRecipe">
        <input type="hidden" name="command" value="openCreationFormRecipe">
        <button>
            <fmt:message key="button.create" bundle="${rb}"/>
        </button>
    </form>
</c:if>
<table>
    <tr>
        <th>
            <fmt:message key="creation_date" bundle="${rb}"/>
        </th>
        <th>
            <fmt:message key="exp_date" bundle="${rb}"/>
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
            <c:if test="${sessionScope.user.role == 'PATIENT'}">
                <fmt:message key="recipe.doctor" bundle="${rb}"/>
            </c:if>
            <c:if test="${sessionScope.user.role == 'DOCTOR'}">
                <fmt:message key="recipe.patient" bundle="${rb}"/>
            </c:if>
        </th>
        <th></th>
    </tr>

    <tbody>
    <c:forEach items="${requestScope.recipes}" var="recipe">
        <td><c:out value="${recipe.createdDate}"/></td>
        <td><c:out value="${recipe.expDate}"/></td>
        <td><c:out value="${recipe.medicamentName}"/></td>
        <td><c:out value="${recipe.medicamentDosage}"/></td>
        <td><c:out value="${recipe.amount}"/></td>
        <td>
            <c:if test="${sessionScope.user.role == 'DOCTOR'}">
                <c:out value="${recipe.patientName}"/>
            </c:if>
            <c:if test="${sessionScope.user.role == 'PATIENT'}">
                <c:out value="${recipe.doctorName}"/>
            </c:if>
        </td>

        <c:if test="${sessionScope.user.role == 'PATIENT'}">
            <td height="30px" align="centre">
                <c:if test="${recipe.expDate >= requestScope.currentDate}">
                    <form action="sendRecipeRequest" method="post"
                          style="display: inline-block; margin: 0;">
                        <input type="hidden" name="command" value="sendRecipeRequest"/>
                        <input type="hidden" name="recipeId" value="${recipe.id}">
                        <select name="requestedPeriod">
                            <option value="1">
                                1 <fmt:message key="recipe.month" bundle="${rb}"/>
                            </option>
                            <option value="2">
                                2 <fmt:message key="recipe.months" bundle="${rb}"/>
                            </option>
                            <option value="3">
                                3 <fmt:message key="recipe.months" bundle="${rb}"/>
                            </option>
                        </select>
                        <input type="submit" value="<fmt:message key="recipe.request" bundle="${rb}"/>"/>
                    </form>
                </c:if>
            </td>
        </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>