<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 17.01.2020
  Time: 22:10
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
            font: 11pt Arial, Helvetica, sans-serif; /* Рубленый шрифт текста */
            margin: 0 20% 0 20%; /* Отступы на странице */
        }

        h1 {
            font-size: 36px; /* Размер шрифта */
            margin: 0; /* Убираем отступы */
            color: #fc6; /* Цвет текста */
        }

        h2 {
            margin-top: 0; /* Убираем отступ сверху */
        }

        #header { /* Верхний блок */
            background: #0080c0; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
        }

        #basket {
            background: #1080c1; /* Цвет фона */
            text-align: right;
            padding: 0 30px 5px 0px; /* Поля вокруг текста */
        }

        #logo-left { /* Левая колонка */

            float: left; /* Обтекание справа */
            width: 400px; /* Ширина колонки */
            border: 1px solid #333; /* Параметры рамки вокруг */

        }

        #logo-right { /* Правая колонка */
            margin: 0px 0px 0px 400px; /* Значения отступов */
            border: 1px solid #333; /* Параметры рамки вокруг */
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
            background: #333; /* Цвет фона */
            padding: 5px; /* Поля вокруг текста */
            color: #fff; /* Цвет текста */
            clear: left; /* Отменяем действие float */
        }
    </style>
</head>
<body>

<div id="header">
    <jsp:include page="header.jsp"/>
</div>

<div id="sidebar">
    <p><a href="controller?command=pacientMain">Medicines</a></p>
    <c:if test="${sessionScope.user.role.name().equals('PACIENT')}">
        <p><a href="controller?command=openOrders">Orders</a></p>
    </c:if>
    <c:if test="${sessionScope.user.role.name().equals('PACIENT') || sessionScope.user.role.name().equals('DOCTOR')}">
        <p><a href="controller?command=openRecipes">Recipes</a></p>
    </c:if>
    <c:if test="${sessionScope.user.role.name().equals('PHARMACIST') || sessionScope.user.role.name().equals('DOCTOR')}">
        <p><a href="XXXXXXXXXXXl">Users</a></p>
    </c:if>
</div>

<div id="content">

    <div style="border: 1px black">
        <h2>Tables Name</h2>
    </div>

    <div style="border: 1px black">
        <c:if test="${requestScope.medicinesInBasket==null && requestScope.medicines!=null}">
            <jsp:include page="medicinesTable.jsp"/>
        </c:if>
        <c:if test="${requestScope.medicinesInBasket!=null}">
            <jsp:include page="basketTable.jsp"/>
        </c:if>
        <c:if test="${requestScope.orders!=null}">
            <jsp:include page="ordersTable.jsp"/>
        </c:if>
        <c:if test="${requestScope.medicinesInOrder!=null}">
            <jsp:include page="medicinesInOrderTable.jsp"/>
        </c:if>
    </div>

</div>

<div id="footer">&copy; Олег Гоголинский</div>
</body>
</html>
