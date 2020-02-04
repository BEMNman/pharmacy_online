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
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="rb"/>

<script type="text/javascript">
    <%@include file="/resources/js/switchLocale.js"%>
</script>

<c:set var="urlLocale">
    <c:url value="?language"/>
</c:set>

<div id="header">
    <div id="logo-left">
        <a href="controller?command=mainPage" class="logo">
            <h1>Pharmacy</h1>
            <h1>Online</h1>
        </a>
    </div>

    <div id="logo-right">
        <div id="locale">
            <body link="black" vlink="black" alink="blue">
            <a class="tool-button" href="javascript:switchLocale('${urlLocale}=en')">EN</a>
            |
            <a class="tool-button" href="javascript:switchLocale('${urlLocale}=ru')">RU</a>
            |
            <a class="tool-button" href="javascript:switchLocale('${urlLocale}=be')">BE</a>
            </body>
        </div>

        <c:if test="${sessionScope.user != null}">
            <div id="username-signout">
                <b style="font-size:20px; padding-right: 1em">
                    <fmt:message key="header.hello" bundle="${rb}"/> ${sessionScope.user.name}!
                </b>

                <c:if test="${sessionScope.user != null}">
                    <form id="signout" action="signOut" method="get"
                          style="display: inline-block; margin: 0;">
                        <input type="hidden" name="command" value="logout"/>
                        <a href="controller?command=logout">
                            <fmt:message key="header.singout" bundle="${rb}"/>
                        </a>
                    </form>
                </c:if>

            </div>
        </c:if>

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
    </div>

</div>


