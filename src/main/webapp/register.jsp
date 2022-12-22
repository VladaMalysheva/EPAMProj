<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%--<%--%>
<%--    User user = (User) request.getSession().getAttribute("user");--%>
<%--    if(user != null){--%>
<%--//        request.setAttribute("user", user);--%>
<%--        response.sendRedirect("/index.jsp");--%>
<%--    }--%>
<%--%>--%>

<c:if test="${user != null}">
    <c:redirect url="/index.jsp"/>
</c:if>
<head>
    <title>Register page</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>


<div class="container my-2">
    <%@include file="includes/alert.jsp"%>
    <div class="card w-50 mx-auto my-4">
        <div class="card-header text-center">User Register</div>
        <div class="card-body">
            <form method="post" action="controller">
                <input name="command" value="register" type="hidden">
                <div class="form-group">
                    <label>Name <b style="color: red">*</b><b style="font-size: small; font-weight: lighter "> From 1 to 20 characters</b> </label>
                    <input type="text" name="name" class="form-control" pattern="^[a-zA-Z]{1,20}$" required placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Surname <b style="color: red">*</b><b style="font-size: small; font-weight: lighter "> From 1 to 20 characters</b></label>
                    <input type="text" name="surname" class="form-control" pattern="^[a-zA-Z]{1,20}$" required placeholder="Enter surname">
                </div>
                <div class="form-group">
                    <label>Patronymic <b style="color: red">*</b><b style="font-size: small; font-weight: lighter "> From 1 to 20 characters</b></label>
                    <input type="text" name="patronymic" class="form-control" pattern="^[a-zA-Z]{1,20}$" required placeholder="Enter patronymic">
                </div>
                <div class="form-group">
                    <label>Phone <b style="font-size: small; font-weight: lighter "> Pay attention on pattern below</b></label>
                    <input type="tel" name="phone" class="form-control" pattern="[+]380\d{9}$" placeholder="+380*********">
                </div>
                <div class="form-group">
                    <label>Login <b style="color: red">*</b><b style="font-size: small; font-weight: lighter "> Begin with letter, end with number or letter(. and - allowed, max 19 symbols)</b> </label>
                    <input type="text" name="login" class="form-control" pattern="^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\d.-]{1,19}$" required placeholder="Enter login">
                </div>
                <div class="form-group">
                    <label>Password <b style="color: red">*</b><b style="font-size: small; font-weight: lighter "> Numbers or letters, from 3 to 25 characters</b></label>
                    <input type="password" name="password" class="form-control" pattern="^\w{3,25}$" required placeholder="******">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="includes/footer.jsp"%>

</body>
</html>


