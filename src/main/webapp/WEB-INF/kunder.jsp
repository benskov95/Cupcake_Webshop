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
            <th>Antal ordrer</th>
            <th>Oprettelsesdato</th>
        </tr>
        </thead>
        <tr>
            <td>1</td>
            <td>Hans</td>
            <td>hansERIK@coolmail.com</td>
            <td>1</td>
            <td>03-03-2020</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Mia</td>
            <td>mia-h@gmail.com</td>
            <td>1</td>
            <td>07-03-2020</td>
        </tr>

    </table>


    <div style="float: right"></div>
    <br>
    <a href="adminstart.html" data-toggle="tooltip" title="Tilbage"><i class="fa fa-arrow-circle-left fa-2x" style="float: right"></i></a>
</div>


<%@include file="../Includes/footer.inc"%>
