<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="invoice" value="${invoice}"/>
<c:set var="order" value="${invoice.getOrder()}"/>
<c:set var="direction" value="${order.getDirection()}"/>
<c:set var="purchaser" value="${order.getUser()}"/>


<html>
<head>
  <%@include file="includes/head.jsp"%>

  <title>Invoice user</title>
</head>
<body>
<div class="d-flex justify-content-center align-items-center">
  <div class="card w-25 mt-4">
    <div class="card-header" style="background-color: antiquewhite;">
      <h5>Invoice<b class="font-weight-light"> # ${invoice.getId()}</b>
      </h5>
    </div>
    <div class="card-body">
      <h5>Bill from:</h5>
      <h5 class="font-weight-light">Name: ${direction.getNameOfReceiver()}</h5>
      <h5 class="font-weight-light">Surname: ${direction.getSurnameOfReceiver()}</h5>
      <h5 class="font-weight-light">Patronymic: ${direction.getPatronymicOfReceiver()}</h5>
      <hr>
      <h5>Bill to:</h5>
      <h5 class="font-weight-light">Name: ${purchaser.getName()}</h5>
      <h5 class="font-weight-light">Surname: ${purchaser.getSurname()}</h5>
      <h5 class="font-weight-light">Patronymic: ${purchaser.getPatronymic()}</h5>
      <hr>
      <h5>Product details</h5>
      <h5 class="font-weight-light">Date: ${order.getDate()}</h5>
      <h5 class="font-weight-light">Point of departure: ${order.getPointOfDeparture()}</h5>
      <h5 class="font-weight-light">Destination: ${order.getDestination()}</h5>
      <h5 class="font-weight-light">Type of cargo: ${order.getTypeOfCargo()}</h5>
      <h5 class="font-weight-light">Total price: ${order.getTotalPrice()}</h5>
      <h5 class="font-weight-light">Currency: ${order.getCurrency()}</h5>
      <hr>
      <h5>Invoice details</h5>
      <h5 class="font-weight-light">Date of creation: ${invoice.getDate()}</h5>
        <form action="controller" method="post">
          <input name="command" value="payInvoice" type="hidden">
          <button type="submit" class="btn btn-primary mt-3">Pay</button>
        </form>


    </div>
  </div>

</div>

<%@include file="includes/footer.jsp"%>

</body>
</html>
