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

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<div class="main">
    <h1>
        <fmt:message key="edit_table.name" bundle="${rb}"/>
    </h1>

    <form action="saveMedicament" method="post" style="display: inline-block; margin: 0;">

        <div>
            <div>
                <input type="text" placeholder="name" name="name" value="${requestScope.medicament.name}"/>
            </div>
            <div>
                <select id="form" name="form">
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

            <div>
                <input type="text"
                       placeholder="<fmt:message key="medicines.dosage_l" bundle="${rb}"/>"
                       name="dosage" value="${requestScope.medicament.dosage}"/>
            </div>
            <div>
                <select id="recipe" name="recipe">
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

            <div>
                <input type="text"
                       placeholder="<fmt:message key="medicines.amount_in_pack_l" bundle="${rb}"/>"
                       name="amountInPack"
                       value="${requestScope.medicament.amountInPack}"/>
            </div>
            <div>
                <input type="number" step="0.01"
                       placeholder="<fmt:message key="medicines.price_l" bundle="${rb}"/>"
                       name="price"
                       value="${requestScope.medicament.price}"/>
            </div>
            <div>
                <input type="number" step="1"
                       placeholder="<fmt:message key="medicines.quantity_l" bundle="${rb}"/>"
                       name="quantity"
                       value="${requestScope.medicament.quantity}"/>
            </div>
            <div>
                <input type="hidden" name="command" value="saveMedicament"/>
                <input type="hidden" name="medicamentId" value="${requestScope.medicament.id}">
                <input type="submit" value="<fmt:message key="button.save" bundle="${rb}"/>"/>
            </div>
        </div>
    </form>
</div>
