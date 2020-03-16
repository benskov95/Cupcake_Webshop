<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/userheader.inc"%>

${applicationScope.email}

<title>Kurv</title>

<p class="lead">Her kan du se din kurvs indhold.</p>

<div class="jumbotron">

    <h1>Bestillinger</h1>
    <br>
    ${applicationScope.email}
    <table class="table table-striped">
        <thead class="thead-light">
        <tr>
            <th>Bund</th>
            <th>Topping</th>
            <th>Antal</th>
            <th>Pris</th>
        </tr>
        </thead>
        <tr>
            <td>Vanilla</td>
            <td>Strawberry</td>
            <td>2</td>
            <td>22 kr</td>
        </tr>
        <tr>
            <td>Chocolate</td>
            <td>Rum/Raisin</td>
            <td>1</td>
            <td>12 kr</td>
        </tr>

    </table>

    <form action="FrontController" method="post">
        <input type="hidden" name="target" value="checkout">
        <input type="hidden" name="email" value=${requestScope.email}>
        <input type="hidden" name="saldo" value=${requestScope.saldo}>

        <br>
        <div style="float: right">Samlet pris: 34 kr</div>
        <br>
        <button type="submit" class="btn btn-primary" style="float: right">Til checkout</button>
    </form>

    <form action="start.html" method="post" style="float: left">
        <button type="submit" class="btn btn-primary" style="float: right">Jeg er ikke færdig...</button>
    </form>

</div>


<%@include file="../Includes/footer.inc"%>