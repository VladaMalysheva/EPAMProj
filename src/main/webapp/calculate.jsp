<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page import="com.example.epamproj.dao.entities.Direction" %>
<!doctype html>
<html lang="en">
<%--<%--%>

<%--  Direction product = (Direction) request.getAttribute("productCalc");--%>
<%--  request.setAttribute("productCalc", product);--%>

<%--%>--%>
<c:set var="productCalc" value="${productCalc}" scope="request"/>
<head>
  <%@include file="includes/head.jsp"%>

  <title>Calculation page</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<%@include file="includes/alert.jsp"%>


<c:if test="${productCalc == null}">
<div class="container py-5 ">
  <div class="card mx-auto d-block text-center border-danger p-3" style="width: 60rem;">
    <div class="card-body">

      <h2 class="display-4">Opps! Looks like you haven't chosen any option.</h2>
      <p class="lead pt-2">
        Go to the home page and choose <strong>one</strong> direction to calculate the cost of services.
      </p>
      <div class="d-flex justify-content-center align-items-center pt-3">
        <a href="index.jsp" class="btn btn-primary">Go Home</a>
      </div>
    </div>
  </div>
</div>
</c:if>
<c:if test="${productCalc != null}">
<div class="container py-5">

  <h1 class="text-center">Welcome to the calculation page!
    <br>
    <small class="text-muted">Here you can easily calculate the delivery price</small>

  </h1>



</div>
<div class="container">
  <div class="card border-info">

    <div class="card-body">                      <%-- Card body--%>
      <div class="container-fluid">
        <div class="row">
          <div class="col-7 pt-4 ">                      <%--User input column--%>
            <form method="post" action="controller">
              <input name="command" value="calculate" type="hidden">
              <h4>Direction: ${productCalc.getName()}</h4>
              <hr class="my-3">
              <h4>Distance: ${productCalc.getDistance()}km</h4>
              <hr class="my-3">
              <h4>Weight(t):</h4>
              <input type="text" name="Weight" class="form-control my-3" pattern="([0-9]+[.])?[0-9]+" required placeholder="Enter Weight">
              <h4>Dimension(m^3):</h4>
              <input type="text" name="Dimension" class="form-control my-3" pattern="([0-9]+[.])?[0-9]+" required placeholder="Enter Dimension">
              <button type="submit" class="btn btn-primary btn-lg mt-3">Calculate</button>
            </form>

          </div>
          <div class="col text-end">              <%--Image column--%>
            <img src="${productCalc.getImage()}" class="rounded float-right img-fluid">

          </div>


        </div>
      </div>
    </div>
   <div class="card-footer " style="background-color: #e3f2fd;">          <%-- Card footer--%>
     <div class="container-fluid">
      <div class="row">
        <div class="col py-2">                                            <%-- Price column--%>
            <c:if test="${totalPrice != null}">
          <p class="h2">Total price: ${totalPrice}</p>
            </c:if>
            <c:if test="${totalPrice == null}">
            <p class="h2">Total price: </p>
            </c:if>

        </div>
        <div class="col text-right py-2">                       <%--Order button column--%>
            <form method="post" action="controller">
                <input name="command" value="goOrder" type="hidden">
            <button type="submit" class="btn btn-success btn-lg">Order</button>
            </form>
        </div>


      </div>
    </div>


    </div>
  </div>
</div>
</c:if>


<%@include file="includes/footer.jsp"%>
</body>
</html>
