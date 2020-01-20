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

<div style="border: 1px black">
    <h2>Basket</h2>
</div>
<form id="saveOrder" method="post">
    <table>
        <tr>
            <th>Name</th>
            <th>Form</th>
            <th>Dosage</th>
            <th>Recipe</th>
            <th>In stock</th>
            <th>Price</th>
            <th>Amount</th>
            <th></th>
        </tr>
        <tbody>

        <tbody>
        <c:forEach items="${sessionScope.medicinesInBasket.keySet()}" var="medicament">
            <tr>
                <td><c:out value="${medicament.name}"/></td>
                <td><c:out value="${medicament.form}"/></td>
                <td><c:out value="${medicament.dosage}"/></td>
                <td><c:out value="${medicament.recipe == true ? 'yes' : 'no'}"/></td>
                <td><c:out value="${medicament.quantity}"/></td>
                <td><c:out value="${medicament.price}"/></td>
                <td><c:out value="${sessionScope.medicinesInBasket.get(medicament)}"/></td>
                <td>
                    <input name="id" hidden value="${medicament.id}">
                    <input name="count"
                           type="number"
                           min="0"
                           max="${medicament.quantity}"
                           value="${sessionScope.medicinesInBasket.get(medicament)}"
                           placeholder="${sessionScope.medicinesInBasket.get(medicament)}"
                           style="width: 4em"/>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <input type="hidden" name="command" value="saveOrder"/>
    <input type="submit" th:href="controller?command=saveOrder"/>
</form>

<a href="controller?command=pacientMain">Cancel</a>

</body>
</html>