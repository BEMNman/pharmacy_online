<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>

<form action="logIn" method="post">
    <input type="text" name="name" placeholder="full name"/>
    <input type="text" name="login" placeholder="login"/>
    <input type="password" name="password" placeholder="password"/>
    <input type="password" name="passwordForCheck" placeholder="repeat password"/>
    <input type="hidden" name="command" value="saveNewPacient"/>
    <input type="submit" value="register"/>
</form>

</body>
</html>
