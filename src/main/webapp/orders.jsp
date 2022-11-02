<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page import="com.example.epamproj.dao.entities.Order" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="en">
<head>
    <title>Orders page</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="h3 py-3">Orders:</div>
    <table class="table table-loaght">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Type of cargo</th>
            <th scope="col">Date</th>
            <th scope="col">Point of departure</th>
            <th scope="col">Destination</th>
            <th scope="col">Process</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="o" items="${orders}">
        <tr>
            <td>${o.getUser().getName()}</td>
            <td>${o.getUser().getSurname()}</td>
            <td>${o.getTypeOfCargo()}</td>
            <td>${o.getDate()}</td>
            <td>${o.getPointOfDeparture()}</td>
            <td>${o.getDestination()}</td>
            <td>
                <c:if test="${o.getStatus() == 'new'}">
                <form method="get" action="controller">
                    <input name="command" value="goToInvoice" type="hidden">
                    <input name="orderId" value="${o.getId()}" type="hidden">
                    <button class="myButton" type="submit">Process</button>
                </form>
                </c:if>
                <c:if test="${o.getStatus() == 'unpaid'}">
            Waiting for payment
                </c:if>

            </td>

        </tr>
        </c:forEach>
        </tbody>
    </table>

<%--    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog" role="document">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header"  style="background-color: antiquewhite;">--%>
<%--                    <h5 class="modal-title" id="exampleModalLabel">Invoice<b class="font-weight-light"> # 1</b>--%>
<%--                    </h5>--%>
<%--                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                        <span aria-hidden="true">&times;</span>--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    <h5>Bill from:</h5>--%>
<%--                    <h5 class="font-weight-light">Name: Ivanov</h5>--%>
<%--                    <h5 class="font-weight-light">Surname: Ivan</h5>--%>
<%--                    <h5 class="font-weight-light">Patronymic: Ivanovich</h5>--%>
<%--                    <hr>--%>
<%--                    <h5>Bill to:</h5>--%>
<%--                    <h5 class="font-weight-light">Name: Vlada</h5>--%>
<%--                    <h5 class="font-weight-light">Surname: Malysheva</h5>--%>
<%--                    <h5 class="font-weight-light">Patronymic: Viktorivna</h5>--%>
<%--                    <hr>--%>
<%--                    <h5>Product details</h5>--%>
<%--                    <h5 class="font-weight-light">Date: 12.08.22</h5>--%>
<%--                    <h5 class="font-weight-light">Point of departure: Kyiv</h5>--%>
<%--                    <h5 class="font-weight-light">Destination: Zaporizhya</h5>--%>
<%--                    <h5 class="font-weight-light">Type of cargo: flowers</h5>--%>
<%--                    <h5 class="font-weight-light">Total price: 18000</h5>--%>
<%--                    <h5 class="font-weight-light">Currency: UAH</h5>--%>
<%--                    <hr>--%>
<%--                    <h5>Invoice details</h5>--%>
<%--                    <h5 class="font-weight-light">Date of creation: NOW</h5>--%>
<%--                    <form>--%>
<%--                        <div class="form-group">--%>
<%--                            <label for="message-text" class="col-form-label">Details:</label>--%>
<%--                            <textarea class="form-control" id="message-text"></textarea>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>--%>
<%--                    <button type="button" class="btn btn-primary">Create invoice</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
</div>

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

<%@include file="includes/footer.jsp"%>
</body>
</html>
