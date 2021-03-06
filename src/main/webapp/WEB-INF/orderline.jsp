<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/adminheader.inc"%>


<title>Ordrer</title>

<p class="lead">Alle registrerede ordrer kan ses her.</p>

<div class="jumbotron">

    <h1>Bestillinger</h1>
    <br>

    <div class="row">


            <form action="FrontController" method="post">
                <input type="hidden" name="target" value="redirect">
                <input type="hidden" name="destination" value="getCustomerOrders">

                <div class="row">

                    <div class="col-xs-2">

                        <div class="form-group">
                            <label for="getCustomerOrders">Søg efter ordrer (Kunde ID):</label>
                            <input type="text" class="form-control" id="getCustomerOrders" name="getCustomerOrders">
                        </div>
                        <p style="color:blue;">${requestScope.result}</p>
                        <button type="submit" class="btn btn-primary">Søg</button>
                    </div>

                    <div class="col">
                    </div>

                </div>
            </form>

        <div class="col-md-1">
        </div>

        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="redirect">
            <input type="hidden" name="destination" value="deleteorder">

            <div class="row">

                <div class="col-xs-2">

                    <div class="form-group">
                        <label for="deleteOrder">Slet ordre (indtast ordre ID):</label>
                        <input type="text" class="form-control" id="deleteOrder" name="deleteOrder">
                    </div>
                    <p style="color:blue;">${requestScope.delete}</p>
                    <button type="submit" class="btn btn-primary">Slet</button>
                </div>

                <div class="col">
                </div>

            </div>
        </form>
    </div>

    <br>

    <table class="table table-striped">
        <thead class="thead-light">
        <tr>
            <th>Ordre ID</th>
            <th>Orderline ID</th>
            <th>Kunde ID</th>
            <th>Bund</th>
            <th>Topping</th>
            <th>Antal</th>
            <th>Pris</th>
            <th>Dato</th>
        </tr>
        </thead>

        <c:forEach var="order" items="${sessionScope.orders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.orderLineId}</td>
            <td>${order.customerId}</td>
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