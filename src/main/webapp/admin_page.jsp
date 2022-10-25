<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.epamproj.dao.UserDAO" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: prost
  Date: 04.10.2022
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("name", "Tom");
%>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<form method="post" action="controller">
    <input name="command" value="delete_user" type="hidden">
    Login: <input name="login"><br>
    <input value="Delete" type="submit">
</form>
<table>
            <%
                out.println(request.getAttribute("users"));
                for (User u: UserDAO.getInstance().getAll()
                     ) {
                    out.println("<ul>");
                    out.println("<li>" + u.getLogin() + "</li>" + "<li>" + u.getName() + "</li>" + "<li>" + u.getSurname()+ "</li>" + "<li>"+ u.getPatronymic() + "</li>");
                    out.println("</ul>");
                    out.println("<hr />");
                }
            %>
</table>
<br>
<%--<a href="register.jsp">Return to the login page</a>--%>
<jsp:include page="register.jsp"/>
</body>
</html>
