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
    <h2>Resipes</h2>
</div>

<table>
    <tr>
        <th>Creation date</th>
        <th>Exp Date</th>
        <th>Medicament</th>
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
            <td><c:out value="${recipe.amount}"/></td>
            <td><c:out value="${recipe.doctorName}"/></td>

            <td height="30px" align="centre">
                <form action="viewOrderDetails" method="post"
                      style="display: inline-block; margin: 0;">
                    <input type="hidden" name="command" value="viewOrderDetails"/>
                    <input type="hidden" name="orderId" value="${order.id}">
                    <input type="submit" value="open"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>