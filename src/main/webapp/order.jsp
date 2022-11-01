<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page import="com.example.epamproj.dao.entities.Direction" %>
<!doctype html>
<html lang="en">
<head>
  <%@include file="includes/head.jsp"%>


  <title>Order page</title>

  <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/checkout/">

  <!-- Bootstrap core CSS -->
  <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="form-validation.css" rel="stylesheet">
</head>
<body>
<jsp:include page="includes/navbar.jsp"/>

<c:if test="${productOrd == null}">
<div class="container py-5 ">
  <div class="card mx-auto d-block text-center border-danger p-3" style="width: 60rem;">
    <div class="card-body">

      <h2 class="display-4">Opps! Looks like you haven't chosen any option.</h2>
      <p class="lead pt-2">
        Go to the home page and choose <strong>one</strong> direction to make an order.
      </p>
      <div class="d-flex justify-content-center align-items-center pt-3">
        <a href="index.jsp" class="btn btn-primary">Go Home</a>
      </div>
    </div>
  </div>
</div>
</c:if>
<c:if test="${productOrd != null}">

<div class="container">
  <div class="py-5 text-center">
    <h2>Order form</h2>
<%--    <p class="lead">Below is an example form built entirely with Bootstrap's form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>--%>
  </div>


  <div class="row">
    <div class="col-md-4 order-md-2 mb-4 py-3">
      <img class="img-fluid rounded" src="${productOrd.getImage()}">
    </div>
    <div class="col-md-8 order-md-1">
      <h4 class="mb-3">Billing address</h4>
      <h6>Direction: ${productOrd.getName()}</h6>
      <hr class="my-3">
      <h6>Distance: ${productOrd.getDistance()}km</h6>
      <hr class="my-3">
      <h6>Weight(t): ${weight}</h6>
      <hr class="my-3">
      <h6>Dimension(m^3): ${dimension}</h6>
      <hr class="my-3">
      <h6>Total price: ${totalPrice}</h6>
      <hr class="my-3">

    </div>
    <div class="container order-md-3">
      <h4 class="mb-2">Delivery details</h4>
      <div class="row">
        <div class="col-md-6 mt-3">
          <h5>Name: ${user.getName()}</h5>
        </div>
        <div class="col-md-6 mt-3">
          <h5>Surname: ${user.getSurname()}</h5>
        </div>
      </div>
      <hr class="mb-3 mt-3">
      <form action="controller" method="post">
        <input name="command" value="order" type="hidden">
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="cargoType">Type of cargo</label>
            <input type="text" class="form-control" id="cargoType" name="typeOfCargo" placeholder="" required>
            <div class="invalid-feedback">
              Type of cargo is required
            </div>
          </div>
          <div class="col-md-6 mb-3">
            <label for="exactAddress">Exact address</label>
            <input type="text" class="form-control" id="exactAddress" name="exactAddress" placeholder="" required>
            <div class="invalid-feedback">
              Exact address is required
            </div>
          </div>
        </div>

        <label for="cc-number">Date of delivery</label>
        <input type="date" class="form-control" id="cc-number" name="dateOfDelivery" placeholder="" required>
        <div class="invalid-feedback">
          Exact address is required
        </div>

        <div class="input-group mb-3 mt-4 ">
          <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect01">Options</label>
          </div>
          <select class="custom-select" id="inputGroupSelect01" name="pointOfDeparture">
            <option selected>Point of departure</option>
            <option value="${productOrd.getPlace1()}">${productOrd.getPlace1()}</option>
            <option value="${productOrd.getPlace2()}">${productOrd.getPlace2()}</option>
          </select>
        </div>

        <div class="input-group mb-3 mt-3">
          <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect02">Options</label>
          </div>
          <select class="custom-select" id="inputGroupSelect02" name="destination">
            <option selected>Destination</option>
            <option value="${productOrd.getPlace1()}">${productOrd.getPlace1()}</option>
            <option value="${productOrd.getPlace2()}">${productOrd.getPlace2()}</option>
          </select>
        </div>

        <hr class="my-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
      </form>
    </div>
  </div>

</div>


</c:if>

<%@include file="includes/footer.jsp"%>
</body>
</html>