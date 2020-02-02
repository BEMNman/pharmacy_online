<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 26.01.2020
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<div style="border: 1px black">
    <h2>Medicines</h2>
</div>
<form name="create" action="openCreationFormMedicament">
    <input type="hidden" name="command" value="openCreationFormMedicament">
    <button>create</button>
</form>
<table style=" border: 2px solid; width: auto; text-align:center">
    <tr>
        <th>Name</th>
        <th>Form</th>
        <th>Dosage</th>
        <th>Recipe</th>
        <th>Amount in pack</th>
        <th>Price</th>
        <th>Quantity</th>
        <th></th>
    </tr>

    <tbody class="w3-centered" bgcolor="white">
    <c:forEach items="${requestScope.medicines}" var="medicament">
        <tr>
            <td><c:out value="${medicament.name}"/></td>
            <td><c:out value="${medicament.form.name().toLowerCase()}"/></td>
            <td><c:out value="${medicament.dosage}"/></td>
            <td><c:out value="${medicament.recipe == true ? 'yes' : 'no'}"/></td>
            <td><c:out value="${medicament.amountInPack}"/></td>
            <td><c:out value="${medicament.price}"/></td>
            <td><c:out value="${medicament.quantity}"/></td>
            <td>
                <form action="editMedicament" method="post"
                      style="display: inline-block; margin: 0;">
                    <input type="hidden" name="command" value="editMedicament"/>
                    <input type="hidden" name="medicamentId" value="${medicament.id}">
                    <input type="submit" value="edit"/>
                </form>
            </td>
            <td>
                <form action="deleteMedicament" method="post"
                      style="display: inline-block; margin: 0;">
                    <input type="hidden" name="command" value="deleteMedicament"/>
                    <input type="hidden" name="medicamentId" value="${medicament.id}">
                    <input type="submit" value="delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
