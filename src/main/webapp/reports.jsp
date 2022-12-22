<%@ page import="com.example.epamproj.dao.entities.User" %>

<!doctype html>
<html lang="en">
<head>
    <title>Reports page</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<%@include file="includes/alert.jsp"%>

<div class="container-fluid w-75">
    <div class="h3 py-3">Reports:</div>
    <form class="form-inline" action="controller" method="get">
        <div class="form-group mb-2">
            <label for="inputDate" class="sr-only">Date</label>
            <input type="date" class="form-control" id="inputDate" required name="date" placeholder="date">
            <input type="hidden" name="command" value="showReports">
        </div>
        <button type="submit" class="btn btn-primary mb-2 mx-3">Confirm delivery date</button>
        <div class="dropdown mb-2">
            <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Destination or point of departure
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Kyiv">Kyiv</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Zhytomyr">Zhytomyr</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Lviv">Lviv</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Odessa">Odessa</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Uzhhorod">Uzhhorod</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Lutsk">Lutsk</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Rivne">Rivne</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Sumy">Sumy</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Kherson">Kherson</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Chernivtsi">Chernivtsi</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Kharkiv">Kharkiv</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=Ternopil">Ternopil</a>
                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=showReports&reportsFilter=None">None</a>
            </div>
        </div>
    </form>
<table class="table table-loaght">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Patronymic</th>
        <th scope="col">Type of cargo</th>
        <th scope="col">Date of invoice creation</th>
        <th scope="col">Date of paying</th>
        <th scope="col">Date of delivery</th>
        <th scope="col">Point of departure</th>
        <th scope="col">Destination</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="r" items="${reports}">
        <tr>
            <td>${r.getInvoice().getOrder().getUser().getName()}</td>
            <td>${r.getInvoice().getOrder().getUser().getSurname()}</td>
            <td>${r.getInvoice().getOrder().getUser().getPatronymic()}</td>
            <td>${r.getInvoice().getOrder().getTypeOfCargo()}</td>
            <td>${r.getInvoice().getDate()}</td>
            <td>${r.getDateOfPaying()}</td>
            <td>${r.getInvoice().getOrder().getDate()}</td>
            <td>${r.getInvoice().getOrder().getPointOfDeparture()}</td>
            <td>${r.getInvoice().getOrder().getDestination()}</td>
            <td>${r.getInvoice().getOrder().getTotalPrice()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<%@include file="includes/footer.jsp"%>
</body>
</html>
