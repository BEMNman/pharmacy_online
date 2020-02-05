<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<div class="main">
    <h1>
        <fmt:message key="recipe.create_new" bundle="${rb}"/>
    </h1>
    <form action="saveRecipe" method="post">

        <div style="display: inline">
            <div>
                <input type="date" placeholder="exp date"
                       min="${requestScope.currentDate}" name="expDate" required/>
            </div>
            <div>
                <select id="medicament" name="medicamentId" required>
                    <option selected></option>
                    <c:forEach items="${requestScope.medicines}" var="medicament">
                        <option value="${medicament.id}">${medicament.name}, ${medicament.dosage}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="number" name="quantity" required
                       min="1" step="1" value="1" maxlength="2"
                       placeholder="quantity"/>
            </div>
            <div>
                <select id="patient" name="patientId" required>
                    <option selected></option>
                    <c:forEach items="${requestScope.patients}" var="patient">
                        <option value="${patient.id}">${patient.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="hidden" name="command" value="saveRecipe"/>
                <input type="submit" value="<fmt:message key="button.save" bundle="${rb}"/>"/>
            </div>
        </div>
    </form>
</div>
