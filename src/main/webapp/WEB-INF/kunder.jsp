<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>


<title>Kunder</title>

<p class="lead">Alle registrerede kunder kan ses her.</p>

<div class="jumbotron">

    <h1>Kunder</h1>
    <br>

    <div>
        <select>
            <option selected="selected" disabled="disabled">Sorter...</option>
            <option value="alfabetisk">Sorter alfabetisk efter kundenavn</option>
        </select>
    </div>
    <br>

    <table class="table table-striped">
        <thead class="thead-light">
        <tr>
            <th>Kunde ID</th>
            <th>Kundenavn</th>
            <th>Kunde e-mail</th>
            <th>Saldo</th>
            <th>Antal ordrer</th>
        </tr>
        </thead>

        <c:forEach var="customer" items="${sessionScope.customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td>${customer.credit}</td>
            <td>${customer.numberOfOrders}</td>
        </tr>
        </c:forEach>

    </table>


    <div style="float: right"></div>
    <br>
    <a href="adminstart.html" data-toggle="tooltip" title="Tilbage"><i class="fa fa-arrow-circle-left fa-2x" style="float: right"></i></a>
</div>


<%@include file="../Includes/footer.inc"%>
