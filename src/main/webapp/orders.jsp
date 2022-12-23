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
<%@include file="includes/alert.jsp"%>

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
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pt-3">
            <c:if test="${pageId != 1}">
                <li class="page-item" id="prev">
                    <a class="page-link" href="${pageContext.request.contextPath}/controller?command=showOrders&pageId=${pageId - 1}" tabindex="-1">Previous</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=showOrders&pageId=${i}">${i}</a></li>
            </c:forEach>
            <c:if test="${pageId lt noOfPages}">
                <li class="page-item" id="prev">
                    <a class="page-link" href="${pageContext.request.contextPath}/controller?command=showOrders&pageId=${pageId + 1}" tabindex="-1">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
    ${pageId=null}
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
