<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.epamproj.dao.entities.Order" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">

<head>
    <%@include file="includes/head.jsp"%>
    <title>Home page</title>
</head>
<body>

<%@include file="includes/navbar.jsp"%>

<div class="container mt-5">
    <ul class="nav nav-tabs nav-fill" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="true">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="invoices-tab" data-toggle="tab" href="#invoices" role="tab" aria-controls="invoices" aria-selected="false">Invoices</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="allOrders-tab" data-toggle="tab" href="#allOrders" role="tab" aria-controls="allOrders" aria-selected="false">Orders history</a>
        </li>
    </ul>

    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active text-center" id="profile" role="tabpanel" aria-labelledby="profile-tab">


            <h4 class="py-4">Welcome to your profile, ${user.getName()}
                <br>
                <small class="text-muted">Your account information:</small></h4>

            <h5>Name: <small class="font-weight-light">${user.getName()}</small></h5>
            <h5>Surname: <small class="font-weight-light">${user.getSurname()}</small></h5>
            <h5>Patronymic: <small class="font-weight-light">${user.getPatronymic()}</small></h5>
            <h5>Phone: <small class="font-weight-light">${user.getPhone()}</small></h5>
            <h5>Login: <small class="font-weight-light">${user.getLogin()}</small></h5>
            <h5>Cash: <small class="font-weight-light">${user.getCash()}</small></h5>


        </div>
        <div class="tab-pane fade text-center" id="invoices" role="tabpanel" aria-labelledby="invoices-tab">
            <table class="table table-loaght">
                <thead>
                <tr>
                    <th scope="col">Invoice number</th>
                    <th scope="col">Type of cargo</th>
                    <th scope="col">Date of creation</th>
                    <th scope="col">Point of departure</th>
                    <th scope="col">Destination</th>
                    <th scope="col">Pay</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${invoices}">
                    <tr>
                        <td>${i.getId()}</td>
                        <td>${i.getOrder().getTypeOfCargo()}</td>
                        <td>${i.getDate()}</td>
                        <td>${i.getOrder().getPointOfDeparture()}</td>
                        <td>${i.getOrder().getDestination()}</td>
                        <td>
                            <form method="get" action="controller">
                                <input name="command" value="goToInvoice" type="hidden">
                                <input name="invoice" value="${i.getId()}" type="hidden">
                                <button type="submit" class="btn btn-primary">Show invoice</button>
                            </form>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>
        <div class="tab-pane fade" id="allOrders" role="tabpanel" aria-labelledby="allOrders-tab">
            <table class="table table-loaght">
                <thead>
                <tr>
                    <th scope="col">Order number</th>
                    <th scope="col">Type of cargo</th>
                    <th scope="col">Date</th>
                    <th scope="col">Point of departure</th>
                    <th scope="col">Destination</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <td>${o.getId()}</td>
                        <td>${o.getTypeOfCargo()}</td>
                        <td>${o.getDate()}</td>
                        <td>${o.getPointOfDeparture()}</td>
                        <td>${o.getDestination()}</td>
                        <td>${o.getStatus()}</td>

                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>

    </div>
</div>

<%@include file="includes/footer.jsp"%>

<style>
    .myButton {
        box-shadow:inset 0px 0px 14px -3px #fce2c1;
        background:linear-gradient(to bottom, #ffc477 5%, #fb9e25 100%);
        background-color:#ffc477;
        border-radius:6px;
        border:1px solid #eeb44f;
        display:inline-block;
        cursor:pointer;
        color:#ffffff;
        font-family:Arial;
        font-size:15px;
        font-weight:bold;
        padding:6px 24px;
        text-decoration:none;
        text-shadow:0px 1px 0px #cc9f52;
    }
    .myButton:hover {
        background:linear-gradient(to bottom, #fb9e25 5%, #ffc477 100%);
        background-color:#fb9e25;
    }
    .myButton:active {
        position:relative;
        top:1px;

    }

</style>
</body>
</html>
