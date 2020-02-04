<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 27.01.2020
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    <%@include file="/resources/css/table_style.css" %>
</style>

<html>
<body>
<div style="border: 1px black">
    <h2>Requests</h2>
</div>
<table>
    <tr>
        <th>Creation date</th>
        <th>Status</th>
        <th>Medicament</th>
        <th>Dosage</th>
        <th>Quantity</th>
        <th>Patient</th>
        <th>Exp date</th>
        <th>Requested period</th>
        <th></th>
    </tr>

    <tbody>
    <c:forEach items="${requestScope.requestsDto}" var="request">
        <tr>
            <td><c:out value="${request.creationDate}"/></td>
            <td><c:out value="${request.status.name()}"/></td>
            <td><c:out value="${request.medicamentName}"/></td>
            <td><c:out value="${request.medicamentDosage}"/></td>
            <td><c:out value="${request.quantity}"/></td>
            <td><c:out value="${request.patientName}"/></td>
            <td><c:out value="${request.expDate}"/></td>
            <td><c:out value="${request.requestedPeriod}"/></td>
            <c:if test="${request.status == 'NEW'}">
                <td>
                    <form action="approveRequest" method="post"
                          style="display: inline-block; margin: 0;">
                        <input type="hidden" name="command" value="approveRequest"/>
                        <input type="hidden" name="requestId" value="${request.id}"/>
                        <input type="submit" value="approve"/>
                    </form>
                    <form action="rejectRequest" method="post"
                          style="display: inline-block; margin: 0;">
                        <input type="hidden" name="command" value="rejectRequest"/>
                        <input type="hidden" name="requestId" value="${request.id}"/>
                        <input type="submit" value="reject"/>
                    </form>
                </td>
            </c:if>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
