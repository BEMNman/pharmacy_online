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
            <fmt:message key="recipe.doctor" bundle="${rb}"/>
        </th>
        <th></th>
    </tr>

    <tbody>
    <c:forEach items="${requestScope.recipes}" var="recipe">
        <tr>
            <td><c:out value="${recipe.createdDate}"/></td>
            <td><c:out value="${recipe.expDate}"/></td>
            <td><c:out value="${recipe.medicamentName}"/></td>
            <td><c:out value="${recipe.medicamentDosage}"/></td>
            <td><c:out value="${recipe.amount}"/></td>
            <td><c:out value="${recipe.doctorName}"/></td>

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
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>