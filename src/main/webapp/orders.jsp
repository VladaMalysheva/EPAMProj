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
        <%for (Order o: (List<Order>)request.getAttribute("orders")) {%>
        <tr>
            <td><%=o.getUser().getName()%></td>
            <td><%=o.getUser().getSurname()%></td>
            <td><%=o.getTypeOfCargo()%></td>
            <td><%=o.getDate()%></td>
            <td><%=o.getPointOfDeparture()%></td>
            <td><%=o.getDestination()%></td>
            <td>
                <form action="controller" method="post">
                    <input name="command" value="process" type="hidden">
                    <button class="btn-primary" type="submit">Process</button>
                </form>
            </td>

        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>
