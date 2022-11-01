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
        <div class="tab-pane fade" id="invoices" role="tabpanel" aria-labelledby="invoices-tab">
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
<%--                <%for (Order o: (List<Order>)request.getAttribute("orders")) {%>--%>
<%--                <tr>--%>
<%--                    <td><%=o.getUser().getName()%></td>--%>
<%--                    <td><%=o.getUser().getSurname()%></td>--%>
<%--                    <td><%=o.getTypeOfCargo()%></td>--%>
<%--                    <td><%=o.getDate()%></td>--%>
<%--                    <td><%=o.getPointOfDeparture()%></td>--%>
<%--                    <td><%=o.getDestination()%></td>--%>
<%--                    <td>--%>

<%--                        <button class="myButton" data-toggle="modal" id="button" data-target="#exampleModal">Process</button>--%>

<%--                    </td>--%>

<%--                </tr>--%>
<%--                <%--%>
<%--                    }--%>
<%--                %>--%>
                </tbody>
            </table>

        </div>

    </div>
</div>

<%@include file="includes/footer.jsp"%>

</body>
</html>
