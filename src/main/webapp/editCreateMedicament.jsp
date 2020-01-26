<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 26.01.2020
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<body>

<div style="border: 1px black">
    <h2>Edit/create medicament</h2>
</div>
<form action="saveMedicament" method="post"
      style="display: inline-block; margin: 0;">
    <tr>
        <ul>
            <td><input type="text" placeholder="name" name="name" value="${requestScope.medicament.name}"/></td>
            <td><input type="radio" placeholder="form" name="form" value="${requestScope.medicament.form.name().toLowerCase()}"/>
            </td>
            <td><input type="text" placeholder="dosage" value="${requestScope.medicament.dosage}"/></td>
            <td><input type="checkbox" placeholder="recipe" name="recipe"
                       value="${requestScope.medicament.recipe == true ? 'yes' : 'no'}"/></td>
            <td><input type="text" placeholder="amount in pack" name="amountInPack" value="${requestScope.medicament.amountInPack}"/></td>
            <td><input type="number" step="0.01" placeholder="price" name="price" value="${requestScope.medicament.price}"/></td>
            <td><input type="number" step="1" placeholder="quantity" name="quantity" value="${requestScope.medicament.quantity}"/></td>

            <input type="hidden" name="command" value="editMedicament"/>
            <input type="hidden" name="medicamentId" value="${medicament.id}">
            <input type="submit" value="save"/>

        </ul>
    </tr>
</form>
</tbody>
</table>
</body>
</html>
