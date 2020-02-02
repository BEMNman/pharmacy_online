<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 27.01.2020
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Pharmacy</title>
    <style>
        body {
            min-width: 800px;
            font: 11pt Arial, Helvetica, sans-serif;
            margin: 0 20% 0 20%;
        }

        h1 {
            font-size: 36px;
            margin: 0;
            color: #fc6;
        }

        h2 {
            margin-top: 0;
        }

        #header {
            background: #0080c0;
            padding: 10px;
        }

        #basket {
            background: #1080c1;
            text-align: right;
            padding: 0 30px 5px 0px;
        }

        #logo-left { /* Левая колонка */

            float: left; /* Обтекание справа */
            width: 400px; /* Ширина колонки */
            /*border: 1px solid #333; !* Параметры рамки вокруг *!*/

        }

        #logo-right { /* Правая колонка */
            margin: 0px 0px 0px 400px; /* Значения отступов */
            /*border: 1px solid #333; !* Параметры рамки вокруг *!*/
            text-align: right;
            padding: 0 20px 5px 0px; /* Поля вокруг текста */
        }

        #locale {
            padding-bottom: 20px; /* Поля вокруг текста */
        }


        #sidebar { /* Левая колонка */
            float: left; /* Обтекание справа */
            border: 1px solid #333; /* Параметры рамки вокруг */
            width: 200px; /* Ширина колонки */
            padding: 5px; /* Поля вокруг текста */
            margin: 10px 10px 20px 0px; /* Значения отступов */
        }

        #content { /* Правая колонка */
            margin: 10px 0px 20px 225px; /* Значения отступов */
            padding: 5px; /* Поля вокруг текста */
            border: 1px solid #333; /* Параметры рамки */
        }

        #footer { /* Нижний блок */
            width: 100%;
            position: fixed;
            left: 0;
            bottom: 0;
            float: bottom;
            /*background: #0080c0; !* Цвет фона *!*/
            padding: 20px; /* Поля вокруг текста */
            color: #fff; /* Цвет текста */
            clear: left; /* Отменяем действие float */

            background: -webkit-linear-gradient(right, #76b852, #8DC26F);
            background: -moz-linear-gradient(right, #76b852, #8DC26F);
            background: -o-linear-gradient(right, #76b852, #8DC26F);
            background: linear-gradient(to left, #76b852, #8DC26F);
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
    </style>
</head>
<body>

<div id="header">
    <jsp:include page="../header.jsp"/>
</div>

<div id="sidebar">
    <jsp:include page="../menu.jsp"/>
</div>

<div id="content">

    <div style="border: 1px black">
        <c:if test="${requestScope.medicines != null
                   && requestScope.messageToPage == null}">
            <jsp:include page="createNewRecipe.jsp"/>
        </c:if>
        <c:if test="${requestScope.messageToPage != null}">
            <jsp:include page="../message.jsp"/>
        </c:if>
    </div>

</div>

<div id="footer">&copy; Олег Гоголинский</div>
</body>
</html>
