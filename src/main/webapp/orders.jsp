<%@ page import="com.example.epamproj.dao.entities.User" %>
<%
    User user = (User) request.getSession().getAttribute("user");
    if(user != null){
        request.setAttribute("user", user);
    }
%>
<!doctype html>
<html lang="en">
<head>
    <title>Orders page</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<%@include file="includes/footer.jsp"%>
</body>
</html>
