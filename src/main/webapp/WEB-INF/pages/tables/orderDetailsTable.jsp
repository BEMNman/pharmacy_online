<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<div style="border: 1px black">
    <h2>Details of order</h2>
</div>
    <table style="border: 2px solid; width: 600px; text-align:center;">
        <tr>
            <th>Name</th>
            <th>Form</th>
            <th>Dosage</th>
            <th>Amount in pack</th>
            <th>Recipe</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>

        <tbody class="w3-centered" bgcolor="white">
        <c:forEach items="${requestScope.medicinesInOrder}" var="medicament">
            <tr>
                <td height="30px" align="left"><c:out value="${medicament.name}"/></td>
                <td height="30px" align="left"><c:out value="${medicament.form}"/></td>
                <td height="30px" align="left"><c:out value="${medicament.dosage}"/></td>
                <td height="30px" align="left"><c:out value="${medicament.amountInPack}"/></td>
                <td><c:out value="${medicament.recipe == true ? 'yes' : 'no'}"/></td>
                <td><c:out value="${medicament.quantity}"/></td>
                <td><c:out value="${medicament.price}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
