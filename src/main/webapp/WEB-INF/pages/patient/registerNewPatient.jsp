<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<style type="text/css">
    <%@include file="/resources/css/style.css"%>
</style>

<html>
<body>
<div class="wrapper">
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
    <div class="content">
        <div class="main">
            <div class="mobile">
                <div class="form">
                    <div class="row">
                        <div class="col-50">
                            <form class="register-form" action="logIn" method="post">
                                <label><fmt:message key="login.full_name" bundle="${rb}"/> </label>
                                <input type="text" required name="name"
                                       placeholder="<fmt:message key="login.full_name" bundle="${rb}"/>"/>
                                <label><fmt:message key="login.name" bundle="${rb}"/> </label>
                                <input type="text" required name="login"
                                       placeholder="<fmt:message key="login.name" bundle="${rb}"/>"/>
                                <label><fmt:message key="login.password" bundle="${rb}"/> </label>
                                <input type="password" required name="password"
                                       placeholder="<fmt:message key="login.password" bundle="${rb}"/>"/>
                                <label><fmt:message key="login.repeat_password" bundle="${rb}"/> </label>
                                <input type="password" required name="passwordForCheck"
                                       placeholder="<fmt:message key="login.repeat_password" bundle="${rb}"/>"/>
                                <input type="hidden" name="command" value="saveNewPatient"/>
                                <button>
                                    <fmt:message key="button.register" bundle="${rb}"/>
                                </button>
                            </form>
                            <div class="button-group">
                                <a href="${pageContext.request.contextPath}controller?command=mainPage">
                                    <fmt:message key="return" bundle="${rb}"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/pages/footer.jsp"/>
</div>
</body>
</html>
