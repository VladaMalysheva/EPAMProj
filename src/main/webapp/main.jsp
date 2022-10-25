<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.epamproj.dao.entities.Direction" %>
<%@ page import="com.example.epamproj.dao.DirectionDAO" %>
<%@ page import="java.sql.SQLException" %>

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
    <title>Home page</title>
</head>
<body>

<%@include file="includes/navbar.jsp"%>

<div class="jumbotron jumbotron-fluid" style="background-image: url(images/banner4.jpg);">
    <div class="container">
        <h1 class="display-4" style="color: white">Fluid jumbotron</h1>
        <p class="lead" style="color: white">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p>
        <hr class="my-4">
        <p style="color: white">It uses utility classes for typography and spacing to space content out within the larger container.</p>
    </div>
</div>



<div class="album py-4 bg-white">
    <div class="container">
        <div class="container">

            <div class="row">
                <div class="col"><h4>Sort by: </h4></div>
                <div class="col">
                    <h4 class="text-right">Filter: </h4>
                    </div>

                <!-- Force next columns to break to new line -->
                <div class="w-100"></div>

                <div class="col mt-2">
                    <div class="custom-control custom-radio custom-control-inline" onClick=submit>
                    <input type="radio" id="customRadioInline1" name="Sort" value="NoneSort" class="custom-control-input">
                    <label class="custom-control-label" for="customRadioInline1">None</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="customRadioInline2" name="Sort" value="name" class="custom-control-input">
                        <label class="custom-control-label" for="customRadioInline2">Name</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="customRadioInline3" name="Sort" value="distance" class="custom-control-input">
                        <label class="custom-control-label" for="customRadioInline3">Distance</label>
                    </div>
                </div>
                <div class="col">
                    <div class="d-flex justify-content-end align-items-end">
                        <button id="btnGroupDrop1" type="button" class="btn dropdown-toggle ml-3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Dropdown
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                            <a class="dropdown-item" href="#">Kyiv</a>
                            <a class="dropdown-item" href="#">Zhytomyr</a>
                            <a class="dropdown-item" href="#">Lviv</a>
                            <a class="dropdown-item" href="#">Odessa</a>
                            <a class="dropdown-item" href="#">Uzhhorod</a>
                            <a class="dropdown-item" href="#">Lutsk</a>
                            <a class="dropdown-item" href="#">Rivne</a>
                            <a class="dropdown-item" href="#">Sumy</a>
                            <a class="dropdown-item" href="#">Kherson</a>
                            <a class="dropdown-item" href="#">Chernivtsi</a>
                            <a class="dropdown-item" href="#">Kharkiv</a>
                            <a class="dropdown-item" href="#">Ternopil</a>
                        </div>
                </div>
                </div>
            </div>

        <hr class="my-4">


        <div class="row">
            <%
                for (Direction d: (List<Direction>)request.getAttribute("products")) {%>
            <div class="col-md-4">
                <div class="card border-dark mb-4 box-shadow text-center">
                    <img class="card-img-top" src="<%=d.getImage()%>" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><%=d.getName()%></h5>
                        <%--                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
                        <div class="d-flex justify-content-center align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary ">Calculate</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Order</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                <%}%>
        </div>

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center pt-3">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>

    <script type="text/javascript">
        function SortFunc() {
            var chkYes = document.getElementById("chkYes");
            var dvPassport = document.getElementById("dvPassport");
            dvPassport.style.display = chkYes.checked ? "block" : "none";
        }
    </script>
<%@include file="includes/footer.jsp"%>
</body>
</html>