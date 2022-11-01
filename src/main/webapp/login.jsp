<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
    <title>Login page</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Log in</div>
        <div class="card-body">
            <form method="post" action="controller">
                <input name="command" value="login" type="hidden">
                <div class="form-group">
                    <label>Login</label>
                    <input type="text" name="login" class="form-control" required placeholder="Enter login">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" required placeholder="Password">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="includes/footer.jsp"%>

</body>
</html>