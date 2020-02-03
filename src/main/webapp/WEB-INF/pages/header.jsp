<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 17.01.2020
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="locale" var="rb" />

<style>
    body {
        font: 11pt Arial, Helvetica, sans-serif; /* Рубленый шрифт текста */
        margin: 0 300px; /* Отступы на странице */
    }

    h1 {
        font-size: 36px; /* Размер шрифта */
        margin: 0; /* Убираем отступы */
        color: #ffff09; /* Цвет текста */
    }

    h2 {
        margin-top: 0; /* Убираем отступ сверху */
    }

    #header { /* Верхний блок */
        background: #4dc015; /* Цвет фона */
        padding: 10px; /* Поля вокруг текста */
    }

    #basket {
        background: #4dc015; /* Цвет фона */
        text-align: right;
        padding: 0 30px 5px 0; /* Поля вокруг текста */
    }

    #logo-left { /* Левая колонка */
        float: left; /* Обтекание справа */
        width: 25%; /* Ширина колонки */
        text-align: center ;
        /*border: 1px solid #333; !* Параметры рамки вокруг *!*/
    }

    #logo-right { /* Правая колонка */
        margin: 0 0 0 600px; /* Значения отступов */
        /*border: 1px solid #333; !* Параметры рамки вокруг *!*/
        text-align: right;
        padding: 0 20px 5px 0; /* Поля вокруг текста */
    }

    #locale {
        padding-bottom: 20px; /* Поля вокруг текста */
    }
</style>

<c:set var="urlLocale">
    <c:url value="controller?language"/>
</c:set>
<script>
    function switchLocale(url) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, false);
        xhr.send();

        if (xhr.status != 200)
            alert( url + ', ' + xhr.status + ', ' + xhr.statusText );
        else
            location.reload(true);
    }
</script>

<html>
<head>
    <meta charset="utf-8">
    <title>Pharmacy</title>

</head>
<body>
<div id="header">
    <div id="logo-left">
        <a href="controller?command=mainPage" class="logo">
            <h1>Pharmacy</h1>
            <h1>Online</h1>
        </a>
    </div>
    <div id="logo-right">
        <div id="locale">
            <a class="tool-button" href="javascript:switchLocale('${urlLocale}=en')">EN</a>
            |
            <a class="tool-button" href="javascript:switchLocale('${urlLocale}=ru')">RU</a>
            |
            <a class="tool-button" href="javascript:switchLocale('${urlLocale}=be')">BE</a>
        </div>
        <c:if test="${sessionScope.user != null}">
            <div id="username">
                <b style="font-size:20px; padding-right: 1em"> <fmt:message key="header.hello" bundle="${rb}"/> ${sessionScope.user.name}! </b>
                <c:if test="${sessionScope.user != null}">
                    <form action="signOut" method="get"
                          style="display: inline-block; margin: 0;">
                        <input type="hidden" name="command" value="logout"/>
                        <a href="controller?command=logout"><fmt:message key="header.singout" bundle="${rb}"/></a>
                    </form>
                </c:if>
            </div>
        </c:if>
    </div>
</div>
<c:if test="${sessionScope.user.role == 'PATIENT'}">
    <div id="basket">
        <form action="openBasket" method="get"
              style="display: inline-block; margin: 0;">
            <a href="controller?command=openBasket">
                <fmt:message key="header.basket" bundle="${rb}"/>:
                <c:if test="${sessionScope.medicinesInBasket == null}">
                    0
                </c:if>
                <c:if test="${sessionScope.medicinesInBasket != null}">
                    ${sessionScope.medicinesInBasket.size()}
                </c:if>
            </a>
        </form>
    </div>
</c:if>
</body>
</html>

