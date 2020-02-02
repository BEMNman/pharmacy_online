<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<body>

<div style="border: 1px black">
    <h2>Medicines</h2>
</div>
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
                <td><c:out value="${medicament.recipe ? 'yes' : 'no'}"/></td>
                <td><c:out value="${medicament.amountInPack}"/></td>
                <td><c:out value="${medicament.price}"/></td>
                <td><c:out value="${medicament.quantity}"/></td>
                    <%--            <c:if test="${sessionScope.user.role == 'PATIENT'}">--%>
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
                        <input type="submit" value="add"/>
                    </form>
                </td>

                    <%--        </c:if>--%>
            </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
