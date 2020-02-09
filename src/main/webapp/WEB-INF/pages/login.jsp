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
    <jsp:include page="header.jsp"/>
    <div class="content">
        <div class="main">
            <div class="mobile">
                <div class="form">
                    <c:if test="${sessionScope.user == null}">
                    <div class="row">
                        <div class="col-50">
                            <form class="login-form" action="login" method="post">
                                <label><fmt:message key="login.name" bundle="${rb}"/> </label>
                                <input type="text" name="login"
                                       placeholder="<fmt:message key="login.name" bundle="${rb}"/>"/>
                                <label><fmt:message key="login.password" bundle="${rb}"/> </label>
                                <input type="password" name="password"
                                       placeholder="<fmt:message key="login.password" bundle="${rb}"/>"/>
                                <input type="hidden" name="command" value="login"/>
                                <button type="submit" value="sign in">
                                    <fmt:message key="login.button" bundle="${rb}"/>
                                </button>
                                <p class="message">
                                    <fmt:message key="login.register" bundle="${rb}"/>
                                    <a href="${pageContext.request.contextPath}controller?command=registerNewPatient">
                                        <fmt:message key="login.create" bundle="${rb}"/>
                                    </a>
                                </p>
                            </form>
                            <c:if test="${requestScope.messageToPage != null}">
                                <h4 style="color: red"><fmt:message key="${requestScope.messageToPage}"
                                                                    bundle="${rb}"/></h4>
                            </c:if>
                            </c:if>
                            <c:if test="${sessionScope.user != null}">
                                <form class="logout-form" action="signOut" method="get"
                                      style="display: inline-block; margin: 0;">
                                    <input type="hidden" name="command" value="logout"/>
                                    <a href="${pageContext.request.contextPath}controller?command=logout">Sign Out</a>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/pages/footer.jsp"/>
</div>
</body>
</html>