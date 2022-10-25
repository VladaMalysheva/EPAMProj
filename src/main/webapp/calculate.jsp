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

<%@include file="includes/footer.jsp"%>
</body>
</html>
