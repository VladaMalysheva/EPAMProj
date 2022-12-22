<%--
  Created by IntelliJ IDEA.
  User: prost
  Date: 06.10.2022
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="includes/head.jsp"%>

    <title>Error</title>
</head>
<body>
<div class="container py-5 ">
    <div class="card mx-auto d-block text-center border-danger p-3" style="width: 60rem;">
        <div class="card-body">

            <h2 class="display-4">Opps! Something went wrong.</h2>
            <p class="lead pt-2">
                Error message: ${Exception.getMessage()}
            </p>
            <div class="d-flex justify-content-center align-items-center pt-3">
                <a href="index.jsp" class="btn btn-primary">Go Home</a>
            </div>
        </div>
    </div>
</div>
${Exception = null}
<%@include file="includes/footer.jsp"%>

</body>
</html>
