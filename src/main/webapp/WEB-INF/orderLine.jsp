<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>


<title>Ordrer</title>

<p class="lead">Alle registrerede ordrer kan ses her.</p>

<div class="jumbotron">

    <h1>Bestillinger</h1>
    <br>

    <div>
        <select>
            <option selected="selected" disabled="disabled">Sorter...</option>
            <option value="alfabetisk">Sorter alfabetisk efter kundenavn</option>
            <option value="kunde_id">Sorter efter kunde ID</option>
            <option value="antal">Sorter efter antal</option>
        </select>
    </div>
    <br>

    <table class="table table-striped">
        <thead class="thead-light">
        <tr>
            <th>Orderline ID</th>
            <th>Ordre ID</th>
            <th>Kunde ID</th>
            <th>Kundenavn</th>
            <th>Kunde e-mail</th>
            <th>Bund</th>
            <th>Topping</th>
            <th>Antal</th>
            <th>Pris</th>
            <th>Dato</th>
        </tr>
        </thead>

        <c:forEach var="order" items="${sessionScope.orders}">
        <tr>
            <td>${order.orderLineId}</td>
            <td>${order.orderId}</td>
            <td>${order.customerId}</td>
            <td>${order.customerName}</td>
            <td>${order.email}</td>
            <td>${order.bottomName}</td>
            <td>${order.toppingName}</td>
            <td>${order.quantity}</td>
            <td>${order.price},-</td>
            <td>${order.date}</td>
        </tr>
        </c:forEach>

    </table>


    <div style="float: right"></div>
    <br>
    <a href="FrontController?target=redirect&destination=adminstart" data-toggle="tooltip" title="Tilbage"><i class="fa fa-arrow-circle-left fa-2x" style="float: right"></i></a>
</div>


<%@include file="../Includes/footer.inc"%>