<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/userheader.inc"%>



<title>Olsker Cupcakes</title>

<div style="text-align: center">
    <h1 class="display-4">Velkommen ${sessionScope.customer.name}.</h1>
    <p class="lead">Har du en sød tand? Hvis ikke, så får du det, efter du har smagt vores cupcakes!</p>
    <br>
    <p class="lead"><em>Bestil her:</em>

    <form action="FrontController" method="post">
    <input type="hidden" name="target" value="kurv">
    <input type="hidden" name="email" value=${sessionScope.customer.email}>
    <input type="hidden" name="saldo" value=${sessionScope.customer.credit}>
    <input type="hidden" name="navn" value="${sessionScope.customer.name}">

        <div class="row">
            <div class="col">
                <label>
                    <select name="bottom">
                        <option selected="selected" disabled="disabled">Vælg bund</option>
                        <c:forEach var="bottom" items="${sessionScope.bottoms}">
                        <option value=${bottom.bottomName}>${bottom.bottomName}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="col">
                <label>
                    <select name="topping">
                        <option selected="selected" disabled="disabled">Vælg topping</option>
                        <c:forEach var="topping" items="${sessionScope.toppings}">
                        <option value=${topping.toppingName}>${topping.toppingName}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="col">
                <label>
                    <select name="quantity">
                        <option selected="selected" disabled="disabled">Vælg antal</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                    </select>
                </label>
                <br>
                <br>
                <button type="submit" class="btn btn-primary">Læg i kurv</button>

            </div>

        </div>

    </form>

</div>

<%@include file="../Includes/footer.inc"%>