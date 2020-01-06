<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>

    <form action="login" method="post">
        <input type="text" name="login"/>
        <input type="password" name="password"/>
        <input type="hidden" name="command" value="login"/>
        <input type="submit" value="log in"/>
    </form>
</body>
</html>