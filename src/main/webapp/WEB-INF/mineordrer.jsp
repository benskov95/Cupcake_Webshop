<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/userheader.inc"%>

<title>Mine ordrer</title>

    <p class="lead">${sessionScope.kundebesked}</p>

    <div class="jumbotron">

        <h1>Bestillinger</h1>
        <br>
        <br>

        <table class="table table-striped">
            <thead class="thead-light">
            <tr>
                <th>Ordre ID</th>
                <th>Bund</th>
                <th>Topping</th>
                <th>Antal</th>
                <th>Pris</th>
                <th>Dato</th>
            </tr>
            </thead>
            <c:forEach var="order" items="${sessionScope.customerOrders}">
            <tr>
                <td>${order.orderId}</td>
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
        <a href="FrontController?target=redirect&destination=start" data-toggle="tooltip" title="Tilbage til forsiden"><i class="fa fa-arrow-circle-left fa-2x" style="float: right"></i></a>
    </div>

<%@include file="../Includes/footer.inc"%>