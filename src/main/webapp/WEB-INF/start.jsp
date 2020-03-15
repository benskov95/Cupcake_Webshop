<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../Includes/userheader.inc"%>

<title>Olsker Cupcakes</title>

<div style="text-align: center">
    <h1 class="display-4">Velkommen.</h1>
    <p class="lead">Har du en sød tand? Hvis ikke, så får du det, efter du har smagt vores cupcakes!</p>
    <br>
    <p class="lead"><em>Bestil her:</em>

    <form action="FrontController" method="post">
    <input type="hidden" name="target" value="start">

        <div class="row">
            <div class="col">
                <label>
                    <select>
                        <option selected="selected" disabled="disabled">Vælg bund</option>
                        <option value="bund_1">Bund 1</option>
                        <option value="bund_2">Bund 2</option>
                        <option value="bund_3">Bund 3</option>
                        <option value="bund_4">Bund 4</option>
                    </select>
                </label>
            </div>
            <div class="col">
                <label>
                    <select>
                        <option selected="selected" disabled="disabled">Vælg topping</option>
                        <option value="topping_1">Topping 1</option>
                        <option value="topping_2">Topping 2</option>
                        <option value="topping_3">Topping 3</option>
                        <option value="topping_4">Topping 4</option>
                    </select>
                </label>
            </div>
            <div class="col">
                <label>
                    <select>
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