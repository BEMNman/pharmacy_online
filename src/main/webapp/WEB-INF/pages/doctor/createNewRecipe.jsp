<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<script type="text/javascript">
    <%@include file="/resources/js/inputValidatorCreateEdit.js"%>
</script>

<div class="main">
    <h2>
        <fmt:message key="recipe.create_new" bundle="${rb}"/>
    </h2>

    <form  action="saveRecipe" method="post">
        <div class="row-form">
            <label>
                <fmt:message key="exp_date" bundle="${rb}"/>
            </label>
            <input type="date" placeholder="exp date"
                   min="${requestScope.currentDate}" name="expDate" required/>
        </div>

        <div class="row-form">
            <label>
                <fmt:message key="medicines.medicament" bundle="${rb}"/>
            </label>
            <select id="medicament" name="medicamentId" required>
                <option selected></option>
                <c:forEach items="${requestScope.medicines}" var="medicament">
                    <option value="${medicament.id}">${medicament.name}, ${medicament.dosage}</option>
                </c:forEach>
            </select>
        </div>

        <div class="row-form">
            <label><fmt:message key="medicines.quantity" bundle="${rb}"/></label>
            <input type="number" id="input-quantity" name="quantity" required
                   min="1" step="1" value="1" maxlength="2"
                   placeholder="quantity"/>
        </div>

        <div class="row-form">
            <label><fmt:message key="recipe.patient" bundle="${rb}"/></label>
            <select id="patient" name="patientId" required>
                <option selected></option>
                <c:forEach items="${requestScope.patients}" var="patient">
                    <option value="${patient.id}">${patient.name}</option>
                </c:forEach>
            </select>
        </div>

        <input type="hidden" name="command" value="saveRecipe"/>
        <button class="button-create-form" type="submit"><fmt:message key="button.save" bundle="${rb}"/></button>
    </form>
</div>
