<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/adminheader.inc"%>

    <title>Title</title>


    <div style="text-align: center">
        <h1 class="display-4">Velkommen til adminsiden.</h1>
        <p class="lead">Her kan du kigge på ordrer og kunder i systemet.</p>
        <br>

        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="orderLine">
            <button type="submit" class="btn btn-primary">Se alle ordrer</button>
            <br>
            <br>
        </form>
        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="customers">
            <button type="submit" class="btn btn-primary">Se alle kunder</button>
            <br>
            <br>
        </form>

        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="redirect">
            <input type="hidden" name="destination" value="start">
            <button type="submit" class="btn btn-primary">Gå til startside</button>
            <br>
            <br>
        </form>

    </div>


<%@include file="../Includes/footer.inc"%>

