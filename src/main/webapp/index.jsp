<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Includes/header.inc"%>



<title>Olsker Cupcakes Login</title>

<div style="text-align: center">
    <h1 class="display-4">Log ind</h1>
    <p class="lead">Indtast e-mail og adgangskode for at fortsætte.</p>
    <p style="color: red">${requestScope.error}</p>
    <p style="color: #1dd11e">${requestScope.nykunde}</p>
    <br>

    <div class="row">

        <div class="col">
        </div>

        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="login">

            <div class="form-group">
                <label for="email">E-mail:</label>
                <input type="text" class="form-control" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="pass">Adgangskode:</label>
                <input type="password" class="form-control" id="pass" name="pass">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Log ind</button>

            <br>
            <br>
            <a href="FrontController?target=redirect&destination=newcustomer">Jeg har ikke en konto </a>
            <br>
        </form>

        <div class="col">
        </div>

    </div>

</div>

<%@include file="Includes/footer.inc"%>