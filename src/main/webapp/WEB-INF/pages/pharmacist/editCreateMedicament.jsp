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
        <fmt:message key="edit_table.name" bundle="${rb}"/>
    </h2>

    <form action="saveMedicament" method="post" style="display: inline-block; margin: 0;">
        <div class="row-form">
            <label><fmt:message key="medicines.name" bundle="${rb}"/></label>
            <input type="text" required placeholder="name" name="name" value="${requestScope.medicament.name}"/>
        </div>
        <div class="row-form">
            <label><fmt:message key="medicines.form" bundle="${rb}"/></label>
            <select id="form" name="form" required>
                <option value="pill">
                    <fmt:message key="medicines.pill" bundle="${rb}"/>
                </option>
                <option value="solution">
                    <fmt:message key="medicines.solution" bundle="${rb}"/>
                </option>
                <option value="powder">
                    <fmt:message key="medicines.powder" bundle="${rb}"/>
                </option>
                <option value="cream">
                    <fmt:message key="medicines.cream" bundle="${rb}"/>
                </option>
                <option value="gel">
                    <fmt:message key="medicines.gel" bundle="${rb}"/>
                </option>
            </select>
            <script>
                form.value = '${requestScope.medicament.form.name().toLowerCase()}';
            </script>
        </div>

        <div class="row-form">
            <label><fmt:message key="medicines.dosage" bundle="${rb}"/></label>
            <input type="text" required
                   placeholder="<fmt:message key="medicines.dosage_l" bundle="${rb}"/>"
                   name="dosage" value="${requestScope.medicament.dosage}"/>
        </div>
        <div class="row-form">
            <label><fmt:message key="medicines.recipe" bundle="${rb}"/></label>
            <select id="recipe" name="recipe" required>
                <option value="true">
                    <fmt:message key="yes" bundle="${rb}"/>
                </option>
                <option value="false">
                    <fmt:message key="no" bundle="${rb}"/>
                </option>
            </select>
            <script>
                recipe.value = '${requestScope.medicament.recipe}';
            </script>
        </div>

        <div class="row-form">
            <label><fmt:message key="medicines.amount_in_pack" bundle="${rb}"/></label>
            <input id="amount-pack" type="number" required step="1" min="1"
                   placeholder="<fmt:message key="medicines.amount_in_pack_l" bundle="${rb}"/>"
                   name="amountInPack"
                   value="${requestScope.medicament.amountInPack}"/>
        </div>
        <div class="row-form">
            <label><fmt:message key="medicines.price" bundle="${rb}"/></label>
            <input id="input-price" type="number" step="0.01" min="0" required
                   placeholder="<fmt:message key="medicines.price_l" bundle="${rb}"/>"
                   name="price"
                   value="${requestScope.medicament.price}"/>
        </div>
        <div class="row-form">
            <label><fmt:message key="medicines.quantity" bundle="${rb}"/></label>
            <input  type="number" id="input-quantity" step="1" min="0" pattern="\d+"
                   placeholder="<fmt:message key="medicines.quantity_l" bundle="${rb}"/>"
                   name="quantity" required
                   value="${requestScope.medicament.quantity}"/>
        </div>

        <input type="hidden" name="command" value="saveMedicament"/>
        <input type="hidden" name="medicamentId" value="${requestScope.medicament.id}">
        <button class="button-create-form" type="submit" ><fmt:message key="button.save" bundle="${rb}"/></button>

    </form>


</div>
