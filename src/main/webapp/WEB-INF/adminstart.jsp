<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/header.inc"%>

    <title>Title</title>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="FrontController?target=redirect&destination=index">Log ud <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <span class="navbar-text" style="margin-right: 20px">
                ${requestScope.email}
            </span>
    </div>
</nav>


<div class="jumbotron">

    <div style="text-align: center">
        <h1 class="display-4">Velkommen admin.</h1>
        <p class="lead">Her kan du kigge på ordrer og kunder i systemet.</p>
        <br>

        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="ordrer">
            <input type="hidden" name="email" value=${requestScope.email}>
            <button type="submit" class="btn btn-primary">Se alle ordrer</button>
            <br>
            <br>
        </form>
        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="kunder">
            <input type="hidden" name="email" value=${requestScope.email}>
            <button type="submit" class="btn btn-primary">Se alle kunder</button>
            <br>
            <br>
        </form>

        <form action="FrontController" method="post">
            <input type="hidden" name="target" value="redirect">
            <input type="hidden" name="destination" value="start">
            <input type="hidden" name="email" value=${requestScope.email}>
            <button type="submit" class="btn btn-primary">Gå til startside</button>
            <br>
            <br>
        </form>

    </div>

    <%@include file="../Includes/footer.inc"%>

