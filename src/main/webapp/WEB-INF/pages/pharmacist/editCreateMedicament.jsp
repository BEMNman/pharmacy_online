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

    <div style="display: inline">
        <div>
            <input type="text" placeholder="name" name="name" value="${requestScope.medicament.name}"/>
        </div>
        <div>
            <select id="form" name="form">
                <option value="pill">pill</option>
                <option value="solution">solution</option>
                <option value="powder">powder</option>
                <option value="cream">cream</option>
                <option value="gel">gel</option>
            </select>
            <script>
                form.value = '${requestScope.medicament.form.name().toLowerCase()}';
            </script>
        </div>

        <div>
            <input type="text" placeholder="dosage" name="dosage" value="${requestScope.medicament.dosage}"/>
        </div>
        <div>
            <select id="recipe" name="recipe">
                <option value="true">yes</option>
                <option value="false">no</option>
            </select>
            <script>
                recipe.value = '${requestScope.medicament.recipe}';
            </script>
        </div>

        <div>
            <input type="text" placeholder="amount in pack" name="amountInPack"
                   value="${requestScope.medicament.amountInPack}"/>
        </div>
        <div>
            <input type="number" step="0.01" placeholder="price" name="price"
                   value="${requestScope.medicament.price}"/>
        </div>
        <div>
            <input type="number" step="1" placeholder="quantity" name="quantity"
                   value="${requestScope.medicament.quantity}"/>
        </div>
        <div>
            <input type="hidden" name="command" value="saveMedicament"/>
            <input type="hidden" name="medicamentId" value="${requestScope.medicament.id}">
            <input type="submit" value="save"/>
        </div>
    </div>
</form>
</tbody>
</table>
</body>
</html>
