<html>
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/logo_pharmacy.jpg" />
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:setBundle basename="locale" var="rb"/>

    <style type="text/css">
        <%@include file="/resources/css/style.css"%>
    </style>
    <title>Pharmacy</title>
</head>

<body>
<div class="wrapper">
    <!--Header Begin-->
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
    <!--Header End-->
    <div class="content">
        <!--Menu Begin-->
        <jsp:include page="/WEB-INF/pages/menu.jsp"/>
        <!--Menu End-->
        <div class="main">
            <!--Table Begin-->
            <div class="mobile">
                <c:if test="${requestScope.messageToPage == null}">
                    <jsp:include page="/WEB-INF/pages/tables/basketTable.jsp"/>
                </c:if>
                <c:if test="${requestScope.messageToPage != null}">
                    <jsp:include page="../error/errorMessage.jsp"/>
                </c:if>
            </div>
            <!--Table End-->
        </div>
    </div>
    <!--Footer Begin-->
    <div class="footer">
        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </div>
    <!--Footer End-->
</div>
<%--		<script src="css3-mediaqueries.js"></script>--%>
</body>
</html>