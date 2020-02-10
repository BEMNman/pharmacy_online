<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ctg" uri="custom-tag" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<script type="text/javascript">
    <%@include file="/resources/js/inputValidatorCreateEdit.js"%>
</script>

<div class="main">
    <h1>
        <fmt:message key="medicines" bundle="${rb}"/>
    </h1>
    <c:if test="${requestScope.messageToPage != null}">
        <jsp:include page="../errorMessage.jsp"/>
    </c:if>
    <c:if test="${sessionScope.user.role =='PHARMACIST'}">
        <form name="create">
            <input type="hidden" name="command" value="openCreationFormMedicament">
            <button>
                <fmt:message key="button.create" bundle="${rb}"/>
            </button>
        </form>
    </c:if>
    <c:if test="${requestScope.messageToPage == null}">
        <table class="bordered">
            <tr>
                <th style="width: 200px">
                    <fmt:message key="medicines.name" bundle="${rb}"/>
                </th>
                <th style="width: 60px">
                    <fmt:message key="medicines.form" bundle="${rb}"/>
                </th>
                <th style="width: 80px">
                    <fmt:message key="medicines.dosage" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.recipe" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.amount_in_pack" bundle="${rb}"/>
                </th>
                <th style="width: 65px">
                    <fmt:message key="medicines.price" bundle="${rb}"/>
                </th>
                <th>
                    <fmt:message key="medicines.quantity" bundle="${rb}"/>
                </th>
                <c:if test="${sessionScope.user.role != 'DOCTOR'}">
                    <th style="width: 175px">
                        <fmt:message key="request.action" bundle="${rb}"/>
                    </th>
                </c:if>
            </tr>

            <tbody>
            <c:forEach items="${requestScope.medicines}" var="medicament">
                <tr>
                    <td id="table-name"><c:out value="${medicament.name}"/></td>
                    <td>
                        <c:choose>
                        <c:when test="${medicament.form.name()=='PILL'}">
                            <fmt:message key="medicines.pill" bundle="${rb}"/>
                        </c:when>
                        <c:when test="${medicament.form.name()=='SOLUTION'}">
                            <fmt:message key="medicines.solution" bundle="${rb}"/>
                        </c:when>
                        <c:when test="${medicament.form.name()=='POWDER'}">
                            <fmt:message key="medicines.powder" bundle="${rb}"/>
                        </c:when>
                        <c:when test="${medicament.form.name()=='CREAM'}">
                            <fmt:message key="medicines.cream" bundle="${rb}"/>
                        </c:when>
                        <c:when test="${medicament.form.name()=='GEL'}">
                            <fmt:message key="medicines.gel" bundle="${rb}"/>
                        </c:when>
                        </c:choose>
                    <td><c:out value="${medicament.dosage}"/></td>
                    <td>
                        <c:if test="${medicament.recipe}">
                            <fmt:message key="yes" bundle="${rb}"/>
                        </c:if>
                        <c:if test="${!medicament.recipe}">
                            <fmt:message key="no" bundle="${rb}"/>
                        </c:if>
                    </td>
                    <td><c:out value="${medicament.amountInPack}"/></td>
                    <td><c:out value="${medicament.price}"/></td>
                    <td><c:out value="${medicament.quantity}"/></td>
                    <c:if test="${sessionScope.user.role=='PATIENT'}">
                        <td>
                            <form style="display: inline-block; margin: 0;">
                                <input type="hidden" name="command" value="addMedicamentInBasket"/>
                                <input type="hidden" name="medicamentId" value="${medicament.id}">
                                <input type="hidden" name="page" value="${requestScope.page}">
                                <input name="count" id="input-quantity" type="number" size="2" min="1" max="${medicament.quantity}"
                                       value="1" placeholder="${1}" style="width: 4em"/>
                                <input type="submit"
                                       value="<fmt:message key="add" bundle="${rb}"/>"/>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${sessionScope.user.role=='PHARMACIST'}">
                        <td>
                            <form style="display: inline-block; margin: 0;">
                                <input type="hidden" name="command" value="editMedicament"/>
                                <input type="hidden" name="medicamentId" value="${medicament.id}">
                                <input id="submit" type="submit" value="<fmt:message key="edit" bundle="${rb}"/>"/>
                            </form>
                            <form
                                    style="display: inline-block; margin: 0;">
                                <input type="hidden" name="command" value="deleteMedicament"/>
                                <input type="hidden" name="medicamentId" value="${medicament.id}">
                                <input type="submit" value="<fmt:message key="delete" bundle="${rb}"/>"/>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagination">
            <ctg:pageLink pageNumber="${requestScope.page}" maxPages="${requestScope.maxPage}" command="mainPage"/>
        </div>
    </c:if>
</div>
