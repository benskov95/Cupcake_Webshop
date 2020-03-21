<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/userheader.inc"%>

<title>Mine ordrer</title>

    <p class="lead">${sessionScope.customerMessage}</p>
    <p style="color: blue">${requestScope.confirm}</p>

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

        <button type="button" class="btn btn-primary btn" data-toggle="modal" data-target="#myModal">Jeg mangler penge</button>
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <br>
<%--                        <h4 class="modal-title">Hej</h4>--%>
                    </div>
                    <div class="modal-body">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="target" value="redirect">
                            <input type="hidden" name="destination" value="addmoney">
                            <div class="form-group">
                                <label for="money">Hvad smager godt?</label>
                                <input type="text" class="form-control" id="money" name="money">
                            </div>
                            <button type="submit" class="btn btn-primary">Prøv</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>

            </div>
        </div>

<%@include file="../Includes/footer.inc"%>