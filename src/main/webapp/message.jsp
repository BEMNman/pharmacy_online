<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 22.01.2020
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<style>
    .row {
        display: -ms-flexbox; /* IE10 */
        display: flex;
        -ms-flex-wrap: wrap; /* IE10 */
        flex-wrap: wrap;
        margin: 0 -16px;
    }

    span.message {
        margin: 20%;
        color: darkblue;
    }

    .btn {
        background-color: #4CAF50;
        color: white;
        padding: 12px;
        margin: 10px 0;
        border: none;
        width: 100%;
        border-radius: 3px;
        cursor: pointer;
        font-size: 17px;
    }


    }
</style>
<div class="row">

<span class="message">
    ${requestScope.orderSuccessful}
<%--    The order placed successfully.--%>
<%--    Thanks for choosing us!--%>
</span>

</div>

</body>
</html>


