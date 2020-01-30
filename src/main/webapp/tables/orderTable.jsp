<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 15.01.2020
  Time: 1:17
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

    .col-25 {
        -ms-flex: 25%; /* IE10 */
        flex: 25%;
    }

    .col-50 {
        -ms-flex: 50%; /* IE10 */
        flex: 50%;
    }

    .col-75 {
        -ms-flex: 75%; /* IE10 */
        flex: 75%;
    }

    .col-25,
    .col-50,
    .col-75 {
        padding: 0 16px;
    }

    .container {
        background-color: #f2f2f2;
        padding: 5px 20px 15px 20px;
        border: 1px solid lightgrey;
        border-radius: 3px;
    }

    input[type=text] {
        width: 100%;
        margin-bottom: 20px;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    label {
        margin-bottom: 10px;
        display: block;
    }

    .icon-container {
        margin-bottom: 20px;
        padding: 7px 0;
        font-size: 24px;
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

    .btn:hover {
        background-color: #45a049;
    }

    span.name {
        width: 5%;
        float: left;
        margin-right: 10%;
    }

    span.price {
        float: right;
        color: grey;
    }

    span.quantity {
        margin-left: 20em;
        float: contour;
        color: grey;
    }

    /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (and change the direction - make the "cart" column go on top) */
    @media (max-width: 800px) {
        .row {
            flex-direction: column-reverse;
        }

        .col-25 {
            margin-bottom: 20px;
        }
    }
</style>
<c:if test="${requestScope.messageToPage != null}">
    <jsp:include page="../message.jsp"/>
</c:if>
<c:if test="${requestScope.messageToPage == null}">
    <div class="row">
        <div class="col-75">
            <div class="col-25">
                <div class="container">
                    <h4>Cart
                        <span class="price" style="color:black">
          <i class="fa fa-shopping-cart"></i>
        </span>
                    </h4>
                    <c:forEach items="${sessionScope.medicinesInBasket.keySet()}" var="medicament">
                        <p>
                        <span class="name">
                                ${medicament.name}
                        </span>
                            <a href="#"></a>
                            <span class="quantity">
                                    ${sessionScope.medicinesInBasket.get(medicament)}
                            </span>
                            <span class="price">
                                    ${sessionScope.medicinesInBasket.get(medicament)*medicament.price}
                            </span>
                        </p>
                    </c:forEach>
                    <hr>
                    <p>Total <span class="price" style="color:black"><b>$${requestScope.totalPrice}</b></span></p>
                </div>
            </div>
            <div class="container">
                <form action="pay" method="get">
                    <div class="row">
                        <div class="col-50">
                            <h3>Payment</h3>
                            <label>Accepted Cards</label>
                            <label for="cname">Name on Card</label>
                            <input type="text" id="cname" name="cardname" placeholder="NAME SURNAME" required
                                   pattern="[A-Z]+ [A-Z]+">
                            <label for="ccnum">Credit card number</label>
                            <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444" required
                                   pattern="[0-9]{4}( -)?[0-9]{4}( -)?[0-9]{4}( -)?[0-9]{4}">
                            <label for="expdate">Exp Date</label>
                            <input type="text" id="expdate" name="expdate" placeholder="06/20" required
                                   pattern="[0-9]{2}/[0-9]{2}">
                            <div class="row">
                                <div class="col-50">
                                    <label for="cvv">CVV</label>
                                    <input type="text" id="cvv" required="required" name="cvv" placeholder="543"
                                           pattern="[0-9]{3}">
                                </div>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" name="command" value="pay">
                    <input type="submit" value="Continue to checkout" class="btn">
                </form>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>

