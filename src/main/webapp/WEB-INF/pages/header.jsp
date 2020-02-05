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

<div class="header">
    <div class="headerContent">

        <div class="logo"><a href="?command=mainPage">Pharmacy<span class="pink">Online</span></a></div>

        <div class="headerContentRight">
            <div class="locale">
                <a class="tool-button" href="javascript:switchLocale('${urlLocale}=en')">EN</a>
                |
                <a class="tool-button" href="javascript:switchLocale('${urlLocale}=ru')">RU</a>
                |
                <a class="tool-button" href="javascript:switchLocale('${urlLocale}=be')">BE</a>
            </div>
            <c:if test="${sessionScope.user != null}">
                <ul class="nav">
                    <li><b id="a"><fmt:message key="header.hello" bundle="${rb}"/> ${sessionScope.user.name}!</b id="a"></li>
                    <li><a href="?command=logout"><fmt:message key="header.singout" bundle="${rb}"/></a></li>
                </ul>
            </c:if>
            <c:if test="${sessionScope.user.role == 'PATIENT'}">
                <div class="basket">
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
</div>


