<html>
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/logo_pharmacy.jpg"/>
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
    <jsp:include page="../header.jsp"/>
    <div class="content">
        <div class="main">
            <div class="mobile">
                <div class="form">
                    <h1>Error ${requestScope['javax.servlet.error.status_code']} </h1>
                    <br/>
                    <h2>Exception: ${requestScope['javax.servlet.error.message']}</h2>
                    <br/>
                    <div class="button-group">
                        <a href="${pageContext.request.contextPath}controller?command=mainPage">
                            <fmt:message key="return" bundle="${rb}"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/pages/footer.jsp"/>
</div>
</body>
</html>