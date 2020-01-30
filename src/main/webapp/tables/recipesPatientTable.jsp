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
<html>
<body>

<div style="border: 1px black">
    <h2>Reсipes</h2>
</div>

<table>
    <tr>
        <th>Creation date</th>
        <th>Exp Date</th>
        <th>Medicament</th>
        <th>Dosage</th>
        <th>Amount</th>
        <th>Doctor's name</th>
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
                            <option value="1">1 month</option>
                            <option value="2">2 months</option>
                            <option value="3">3 months</option>
                        </select>
<%--                        <input type="number" min="1" max="3" name="requestedPeriod">--%>
                        <input type="submit" value="request"/>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>