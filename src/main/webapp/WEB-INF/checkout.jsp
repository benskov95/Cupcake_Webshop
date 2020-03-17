<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/userheader.inc"%>


<title>Checkout</title>

<div style="text-align: center">
    <h1 class="display-4">Tak for din bestilling ${sessionScope.navn}!</h1>
    <br>
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
        <tr>
            <td>${cupcake.bottomName}</td>
            <td>${cupcake.toppingName}</td>
            <td>${cupcake.quantity}</td>
            <td>${cupcake.combinedPrice} kr</td>
        </tr>
        </c:forEach>
    </table>
    <br>
    <p class="lead" style="font-weight: bold">Samlet pris: ${sessionScope.totalPrice} kr</p>
    <p class="lead">Din ordre forberedes og er snart klar til afhentning.</p>
    <br>
</div>

<div style="text-align: center">
    <a href="FrontController?target=redirect&destination=start" data-toggle="tooltip" title="Tilbage til forsiden"><i class="fa fa-arrow-circle-left fa-2x"></i></a>
</div>


<%@include file="../Includes/footer.inc"%>