<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 27.01.2020
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<body>

<div style="border: 1px black">
    <h2>Create new recipe</h2>
</div>
<form action="saveRecipe" method="post"
      style="display: inline-block; margin: 0;">

    <div style="display: inline">
        <div>
            <input type="date" placeholder="exp date"
                   min="${requestScope.currentDate}" name="expDate" required/>
        </div>
        <div>
            <select id="medicament" name="medicamentId" required>
                <option selected> </option>
                <c:forEach items="${requestScope.medicines}" var="medicament">
                    <option value="${medicament.id}">${medicament.name}, ${medicament.dosage}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <input type="number" name="quantity" required
                   min="1" step="1" value="1" maxlength="2"
                   placeholder="quantity" />
        </div>
        <div>
            <select id="patient" name="patientId" required>
                <option selected> </option>
                <c:forEach items="${requestScope.patients}" var="patient">
                    <option value="${patient.id}">${patient.name}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <input type="hidden" name="command" value="saveRecipe"/>
            <input type="submit" value="save"/>
        </div>
    </div>
</form>
</tbody>
</table>
</body>
</html>
