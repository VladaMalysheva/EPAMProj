<%@ page import="com.example.epamproj.dao.entities.User" %>
<!doctype html>
<html lang="en">
<%
  User user = (User) request.getSession().getAttribute("user");
  if(user != null){
    request.setAttribute("user", user);
  }
%>
<head>
  <%@include file="includes/head.jsp"%>

  <title>Calculation page</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>


<%--<%if(request.getAttribute("calculatedDirection") == null){%>--%>
<%--<div class="container py-5 ">--%>
<%--  <div class="card mx-auto d-block text-center border-danger p-3" style="width: 60rem;">--%>
<%--    <div class="card-body">--%>

<%--      <h2 class="display-4">Opps! Looks like you haven't chosen any option.</h2>--%>
<%--      <p class="lead pt-2">--%>
<%--        Go to the home page and choose <strong>one</strong> direction to calculate the cost of services.--%>
<%--      </p>--%>
<%--      <div class="d-flex justify-content-center align-items-center pt-3">--%>
<%--        <a href="index.jsp" class="btn btn-primary">Go Home</a>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</div>--%>
<%--<%} else{%>--%>
<div class="container py-5">

  <h1 class="text-center">Welcome to the calculation page!
    <br>
    <small class="text-muted">Here you can easily calculate the delivery price</small>

  </h1>

<%--  <p class="text-center fw-light fs-4">Here you can easily calculate the delivery price</p>--%>


</div>
<div class="container">
  <div class="card border-info">

    <div class="card-body">                      <%-- Card body--%>
      <div class="container-fluid">
        <div class="row">
          <div class="col-7 py-5">                      <%--User input column--%>
            <h2>Direction: Kharkiv-Uzhhorod</h2>
            <hr class="my-4">
            <h2>Distance: 1286</h2>
            <hr class="my-4">
            <h2>Weight: 1200t</h2>
            <hr class="my-4">
            <h2>Dimension: 100m^3</h2>

          </div>
          <div class="col text-end">              <%--Image column--%>
            <img src="images/Kharkiv-Uzhhorod.jpg" class="rounded float-right img-fluid">

          </div>


        </div>
      </div>
    </div>
   <div class="card-footer " style="background-color: #e3f2fd;">          <%-- Card footer--%>
     <div class="container-fluid">
      <div class="row">
        <div class="col py-2">                                            <%-- Price column--%>
          <p class="h1">Total price: </p>

        </div>
        <div class="col text-right py-3 mx-5">                       <%--Order button column--%>
          <button type="button" class="btn btn-success btn-lg">Order</button>
        </div>


      </div>
    </div>


    </div>
  </div>
</div>
<%--<%}%>--%>


<%@include file="includes/footer.jsp"%>
</body>
</html>
