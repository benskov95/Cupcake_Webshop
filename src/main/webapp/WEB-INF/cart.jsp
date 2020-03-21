<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/userheader.inc"%>

<title>Kurv</title>

<p class="lead">Her kan du se din kurvs indhold.</p>
<p style="color: red">${requestScope.insufficientFunds}<br>${requestScope.insufficientFundsTwo}</p>

<div class="jumbotron">

    <h1>Bestillinger</h1>
    <br>


    <form action="FrontController" method="post">

    <table class="table table-striped">
        <thead class="thead-light">
        <tr>
            <th>Bund</th>
            <th>Topping</th>
            <th>Antal</th>
            <th>Pris</th>
        </tr>
        </thead>
        <c:forEach var="cupcake" items="${sessionScope.cupcakes}">
            <input type="hidden" name="counts" value=${cupcake.count}>
        <tr>
            <td>${cupcake.bottomName}</td>
            <td>${cupcake.toppingName}</td>
            <td>${cupcake.quantity}</td>
            <td>${cupcake.combinedPrice} kr</td>
            <td>
                <form action="FrontController" method="post">
                <input type="hidden" name="target" value="redirect">
                <input type="hidden" name="destination" value="removecupcake">
                <input type="hidden" name="cupcakeNumber" value=${cupcake.count}>
                <button type="submit" class="btn btn-danger" style="float: right">Fjern</button>
                </form>
            </td>
        </tr>
        </c:forEach>

    </table>
    </form>


    <form action="FrontController" method="post">
        <input type="hidden" name="target" value="checkout">
        <br>
        <div style="float: right">Samlet pris: ${sessionScope.totalPrice} kr</div>
        <br>
        <button type="submit" class="btn btn-primary" style="float: right">Betal</button>
    </form>

    <form action="FrontController?target=redirect&destination=start" method="post" style="float: left">
        <button type="submit" class="btn btn-primary" style="float: right">Jeg er ikke færdig...</button>
    </form>

</div>


<%@include file="../Includes/footer.inc"%>