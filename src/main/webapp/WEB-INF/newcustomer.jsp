<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../Includes/header.inc"%>


<title>Ny Kunde</title>

<div style="text-align: center">

    <h1 class="display-4">Opret bruger</h1>
    <p class="lead">Skriv venligst dit navn, en e-mail og vælg en adgangskode for at oprette din konto.</p>
    <br>
    <p style="color: red">${requestScope.emailFejl}</p>

    <div class="row">

        <div class="col-md">
        </div>

        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="newcustomer">


            <div class="form-group">
                <label for="navn">Navn:</label>
                <input type="text" class="form-control" id="navn" name="navn">
            </div>
            <div class="form-group">
                <label for="email">E-mail:</label>
                <input type="text" class="form-control" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="pass">Adgangskode:</label>
                <input type="password" class="form-control" id="pass" name="pass">
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Opret konto</button>
        </form>

        <div class="col-md">
        </div>

    </div>

</div>

<%@include file="../Includes/footer.inc"%>
