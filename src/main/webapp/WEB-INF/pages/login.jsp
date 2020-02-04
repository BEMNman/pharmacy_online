<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<style type="text/css">
    <%@include file="/resources/css/styles.css"%>
</style>

<html>
<jsp:include page="header.jsp"/>
<div class="login-page">
    <div class="form">
        <c:if test="${sessionScope.user == null}">
            <form class="login-form" action="login" method="post">
                <input type="text" name="login"
                       placeholder="<fmt:message key="login.name" bundle="${rb}"/>"
                />
                <input type="password" name="password"
                       placeholder="<fmt:message key="login.password" bundle="${rb}"/>"
                />
                <input type="hidden" name="command" value="login"/>
                <button type="submit" value="sign in">
                    <fmt:message key="login.button" bundle="${rb}"/>
                </button>
                <p class="message">
                        <fmt:message key="login.register" bundle="${rb}"/>
                    <a href="controller?command=registerNewPatient">
                        <fmt:message key="login.create" bundle="${rb}"/>
                    </a>
                <h4>${requestScope.messageToPage}</h4>
                </p>
            </form>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <form class="logout-form" action="signOut" method="get"
                  style="display: inline-block; margin: 0;">
                <input type="hidden" name="command" value="logout"/>
                <a href="controller?command=logout">Sign Out</a>
            </form>
        </c:if>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</html>