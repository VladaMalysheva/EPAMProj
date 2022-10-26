<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page import="com.example.epamproj.dao.entities.Direction" %>
<!doctype html>
<html lang="en">
<%
  User user = (User) request.getSession().getAttribute("user");
  if(user != null){
    request.setAttribute("user", user);
  }
  Direction product = (Direction) request.getAttribute("productCalc");
  request.setAttribute("productCalc", product);

//  float tariffWeight = (Float) request.getAttribute("tariffWeight");
//  float tariffDimension = (Float) request.getAttribute("tariffDimension");
//  float tariffDistance = (Float) request.getAttribute("tariffDistance");
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



</div>
<div class="container">
  <div class="card border-info">

    <div class="card-body">                      <%-- Card body--%>
      <div class="container-fluid">
        <div class="row">
          <div class="col-7 pt-4 ">                      <%--User input column--%>
            <form method="post" action="controller">
              <input name="command" value="calculate" type="hidden">
              <input name="productId" value=<%=((Direction) request.getSession().getAttribute("productCalc")).getId()%> type="hidden">
              <h4>Direction: <%=((Direction) request.getSession().getAttribute("productCalc")).getName()%></h4>
              <hr class="my-3">
              <h4>Distance: <%=((Direction) request.getSession().getAttribute("productCalc")).getDistance()%>km</h4>
              <hr class="my-3">
              <h4>Weight(t):</h4>
              <input type="number" name="Weight" class="form-control my-3" pattern="([0-9]+[.])?[0-9]+" required placeholder="Enter Weight">
              <h4>Dimension(m^3):</h4>
              <input type="number" name="Dimension" class="form-control my-3" pattern="([0-9]+[.])?[0-9]+" required placeholder="Enter Dimension">
              <button type="submit" class="btn btn-primary btn-lg mt-3">Calculate</button>
            </form>

          </div>
          <div class="col text-end">              <%--Image column--%>
            <img src="<%=((Direction) request.getSession().getAttribute("productCalc")).getImage()%>" class="rounded float-right img-fluid">

          </div>


        </div>
      </div>
    </div>
   <div class="card-footer " style="background-color: #e3f2fd;">          <%-- Card footer--%>
     <div class="container-fluid">
      <div class="row">
        <div class="col py-2">                                            <%-- Price column--%>
          <p class="h2">Total price: </p>

        </div>
        <div class="col text-right py-2">                       <%--Order button column--%>
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
