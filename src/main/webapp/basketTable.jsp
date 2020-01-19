<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 15.01.2020
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<table class="w3-centered" bgcolor="#808080" style="border: 2px solid; width: 600px; text-align:center;">

    <tr>
        <th class="w3-centered" bgcolor="white">Name</th>
        <th class="w3-centered" bgcolor="white">Form</th>
        <th class="w3-centered" bgcolor="white">Dosage</th>
        <th class="w3-centered" bgcolor="white">Recipe</th>
        <th class="w3-centered" bgcolor="white">In stock</th>
        <th class="w3-centered" bgcolor="white">Price</th>
        <th class="w3-centered" bgcolor="white">Amount</th>
        <th class="w3-centered" bgcolor="white"></th>
    </tr>

    <tbody class="w3-centered" bgcolor="white">
    <c:forEach items="${requestScope.medicinesInBasket.keySet()}" var="medicament">
        <tr>
            <td height="30px" align="left"><c:out value="${medicament.name}"/></td>
            <td height="30px" align="left"><c:out value="${medicament.form}"/></td>
            <td height="30px" align="left"><c:out value="${medicament.dosage}"/></td>

            <c:if test="${medicament.recipe == true}">
                <td><c:out value="yes"/></td>
            </c:if>
            <c:if test="${medicament.recipe == false}">
                <td><c:out value="no"/></td>
            </c:if>

            <td><c:out value="${medicament.quantity}"/></td>
            <td><c:out value="${medicament.price}"/></td>
            <td><c:out value="${requestScope.medicinesInBasket.get(medicament)}"/></td>
            <td height="30px" align="centre">
                <form action="addMedicamentInBasket" method="post"
                      style="display: inline-block; margin: 0;">
                    <input type="hidden" name="command" value="addMedicamentInBasket"/>
                    <input type="hidden" name="medicamentId" value="${medicament.id}">
                    <input type="submit" value="add"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
