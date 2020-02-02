<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 27.01.2020
  Time: 13:36
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
<form name="create" action="openCreationFormRecipe">
    <input type="hidden" name="command" value="openCreationFormRecipe">
    <button>new recipe</button>
</form>
<table>
    <tr>
        <th>Creation date</th>
        <th>Exp Date</th>
        <th>Medicament</th>
        <th>Dosage</th>
        <th>Amount</th>
        <th>Patient's name</th>
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
            <td><c:out value="${recipe.patientName}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
