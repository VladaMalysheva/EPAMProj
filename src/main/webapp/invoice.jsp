<%@ page import="com.example.epamproj.dao.entities.Order" %>
<%@ page import="javax.print.attribute.standard.Destination" %>
<%@ page import="com.example.epamproj.dao.entities.Direction" %>
<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.Instant" %><%--
  Created by IntelliJ IDEA.
  User: prost
  Date: 01.11.2022
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%

  Order order = (Order) request.getAttribute("orderInv");
  System.out.println(order);
  Direction direction = order.getDirection();
  User user = order.getUser();
  long millis=System.currentTimeMillis();
  Date date=new Date(millis);

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="includes/head.jsp"%>

  <title>Input</title>
</head>
<body>
<div class="d-flex justify-content-center align-items-center">
  <div class="card w-25 mt-4">
    <div class="card-header" style="background-color: antiquewhite;">
      <h5>Invoice<b class="font-weight-light"> # <%=order.getId()%></b>
      </h5>
    </div>
    <div class="card-body">
      <h5>Bill from:</h5>
      <h5 class="font-weight-light">Name: <%=direction.getNameOfReceiver()%></h5>
      <h5 class="font-weight-light">Surname: <%=direction.getSurnameOfReceiver()%></h5>
      <h5 class="font-weight-light">Patronymic: <%=direction.getPatronymicOfReceiver()%></h5>
      <hr>
      <h5>Bill to:</h5>
      <h5 class="font-weight-light">Name: <%=user.getName()%></h5>
      <h5 class="font-weight-light">Surname: <%=user.getSurname()%></h5>
      <h5 class="font-weight-light">Patronymic: <%=user.getPatronymic()%></h5>
      <hr>
      <h5>Product details</h5>
      <h5 class="font-weight-light">Date: <%=order.getDate()%></h5>
      <h5 class="font-weight-light">Point of departure: <%=order.getPointOfDeparture()%></h5>
      <h5 class="font-weight-light">Destination: <%=order.getDestination()%></h5>
      <h5 class="font-weight-light">Type of cargo: <%=order.getTypeOfCargo()%></h5>
      <h5 class="font-weight-light">Total price: <%=order.getTotalPrice()%></h5>
      <h5 class="font-weight-light">Currency: <%=order.getCurrency()%></h5>
      <hr>
      <h5>Invoice details</h5>
      <h5 class="font-weight-light">Date of creation: <%=date%></h5>


        <form action="controller" method="post">
          <div class="form-group">
            <label for="message-text" class="col-form-label">Details:</label>
            <textarea class="form-control" id="message-text" name="details"></textarea>
          <input name="command" value="createInvoice" type="hidden">
          <input name="date" value="<%=date%>" type="hidden">
            <input name="orderInv" value="<%=order.getId()%>" type="hidden">


            <button type="submit" class="btn btn-primary mt-3">Create invoice</button>
          </div>
        </form>

    </div>
  </div>

</div>

<%@include file="includes/footer.jsp"%>

</body>
</html>
