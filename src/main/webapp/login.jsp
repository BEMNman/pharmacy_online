<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>

<c:if test="${sessionScope.user == null}">
    <form action="login" method="post">
        <input type="text" name="login" placeholder="login"/>
        <input type="password" name="password" placeholder="password"/>
        <input type="hidden" name="command" value="login"/>
        <input type="submit" value="sign in"/>
    </form>
        <a href="controller?command=registerNewPacient">Register new user</a>
</c:if>
</body>
</html>