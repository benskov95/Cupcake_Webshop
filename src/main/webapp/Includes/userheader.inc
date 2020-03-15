<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</head>

<header style="text-align: center">
    <a href="FrontController?target=redirect&destination=start"><img src="Images/Cupcake%20banner.PNG" class="img-fluid" title="Olsker Cupcakes" alt="Olsker Cupcakes" style="width: 75%"></a>
</header>

<body background="Images/Cupcake%20baggrund.jpg">

<div class="container">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active" style="right: auto">
                <a class="nav-link" href="index.jsp">Log ud</a>
            </li>
            <li class="nav-item active" style="right: auto">
                <a class="nav-link" href="index.jsp">Mine ordrer</a>
            </li>
            <li class="nav-item active" style="right: auto">
                <a class="nav-link disabled" href="#">${requestScope.saldo},-</a>
            </li>
        </ul>

        <span class="navbar-text" style="margin-right: 20px">
            ${requestScope.email}
            </span>
        <span>
                <a href="kurv.jsp"><i class="fa fa-shopping-bag fa-2x"></i></a>
            </span>
    </div>
</nav>

<div class="jumbotron">