<html>
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/img/logo_pharmacy.jpg" />
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <c:if test="${requestScope.medicines != null
                   && requestScope.messageToPage == null}">
                    <jsp:include page="/WEB-INF/pages/tables/medicinesTable.jsp"/>
                </c:if>
                <c:if test="${requestScope.messageToPage != null}">
                    <jsp:include page="../errorMessage.jsp"/>
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
</body>
</html>
