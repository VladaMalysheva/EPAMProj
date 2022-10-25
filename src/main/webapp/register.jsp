<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%
    User user = (User) request.getSession().getAttribute("user");
    if(user != null){
//        request.setAttribute("user", user);
        response.sendRedirect("/index.jsp");
    }
%>
<head>
    <title>Register page</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Register</div>
        <div class="card-body">
            <form method="post" action="controller">
                <input name="command" value="register" type="hidden">
                <div class="form-group">
                    <label>Name <b style="color: red">*</b> </label>
                    <input type="text" name="name" class="form-control" required placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Surname <b style="color: red">*</b></label>
                    <input type="text" name="surname" class="form-control" required placeholder="Enter surname">
                </div>
                <div class="form-group">
                    <label>Patronymic</label>
                    <input type="text" name="patronymic" class="form-control" placeholder="Enter patronymic">
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input type="tel" name="phone" class="form-control" pattern="[+]380\d{9}$" placeholder="+380*********">
                </div>
                <div class="form-group">
                    <label>Login <b style="color: red">*</b></label>
                    <input type="text" name="login" class="form-control" required placeholder="Enter login">
                </div>
                <div class="form-group">
                    <label>Password <b style="color: red">*</b></label>
                    <input type="password" name="password" class="form-control" required placeholder="******">
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

<%--<html>--%>
<%--<body>--%>
<%--    <form method="post" action="controller">--%>
<%--        <input name="command" value="login" type="hidden">--%>
<%--        Name: <input name="name"><br>--%>
<%--        Surname: <input name="surname"><br>--%>
<%--        Patronymic: <input name="patronymic"><br>--%>
<%--        Phone: <input name="phone"><br>--%>
<%--        Login: <input name="login"><br>--%>
<%--        Password: <input name="password" type="password"><br>--%>
<%--        <input value="Register" type="submit">--%>

<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
