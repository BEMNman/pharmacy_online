<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 17.01.2020
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
    <%@include file="/resources/css/main.css" %>
    <%@include file="/resources/css/page_style.css"%>
</style>


<html>
<head>
    <title>Pharmacy</title>
</head>
<body>
<div id="header">
    <jsp:include page="../header.jsp"/>
</div>

<div id="sidebar">
    <jsp:include page="../menu.jsp"/>
</div>

<div id="content">

    <div style="border: 1px black">
        <c:if test="${requestScope.medicines != null
                   && requestScope.messageToPage == null}">
            <jsp:include page="/WEB-INF/pages/tables/medicinesTable.jsp"/>
        </c:if>
        <c:if test="${requestScope.messageToPage != null}">
            <jsp:include page="/WEB-INF/pages/message.jsp"/>
        </c:if>
    </div>

</div>
<div id="footer">
    <jsp:include page="/WEB-INF/pages/footer.jsp"/>
</div>
</body>
</html>
