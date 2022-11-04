<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="index.jsp">Cargo delivery</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
        <c:if test="${(user != null) && user.getRole() == 'client'}">

        <li class="nav-item">
          <a class="nav-link" href="order.jsp">Order</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="controller?command=showOrders">Cabinet</a>
        </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link" href="calculate.jsp">Calculate</a>
        </li>
        <c:if test="${(user != null) and (user.getRole() == 'admin')}">
        <li class="nav-item">
          <a class="nav-link" href="controller?command=showOrders">Orders</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="controller?command=showReports">Reports</a>
        </li>
        </c:if>
        <c:if test="${user != null}">
            <li class="nav-item">
          <form method="post" action="controller">
            <input name="command" value="logout" type="hidden">
            <button type="submit" class="btn btn-primary">Logout</button>
          </form>
        </li>
        </c:if>
        <c:if test="${user == null}">

        <li class="nav-item">
          <a class="nav-link" href="login.jsp">Log in</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="register.jsp">Register</a>
        </li>
        </c:if>

      </ul>
    </div>
  </div>
</nav>