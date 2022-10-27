<%@ page import="com.example.epamproj.dao.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.epamproj.dao.entities.Direction" %>
<%@ page import="com.example.epamproj.dao.DirectionDAO" %>
<%@ page import="java.sql.SQLException" %>


<!doctype html>
<html lang="en">

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
                    <div class="custom-control custom-radio custom-control-inline" onClick="window.location = '/controller?command=getProducts&Sort=NoneSort';">
                    <input type="radio" id="customRadioInline1" name="Sort" value="NoneSort" class="custom-control-input">
                    <label class="custom-control-label" for="customRadioInline1">None</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline" onClick="window.location = '/controller?command=getProducts&Sort=name';">
                        <input type="radio" id="customRadioInline2" name="Sort" value="name" class="custom-control-input">
                        <label class="custom-control-label" for="customRadioInline2">Name</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline" onClick="window.location = '/controller?command=getProducts&Sort=distance';">
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
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Kyiv">Kyiv</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Zhytomyr">Zhytomyr</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Lviv">Lviv</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Odessa">Odessa</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Uzhhorod">Uzhhorod</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Lutsk">Lutsk</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Rivne">Rivne</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Sumy">Sumy</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Kherson">Kherson</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Chernivtsi">Chernivtsi</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Kharkiv">Kharkiv</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=Ternopil">Ternopil</a>
                                <a class="dropdown-item" onclick="parentNode.submit();" href="${pageContext.request.contextPath}/controller?command=getProducts&Filter=None">None</a>
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
                        <p class="card-text">Distance: <%=d.getDistance()%></p>
                        <div class="d-flex justify-content-center align-items-center">
                            <div class="btn-group">
                            <form action="controller" method="get" name="foorm">

                                        <button type="button" id="b1" onClick="submitForm(this, <%=d.getId()%>)" class="btn btn-sm btn-outline-secondary ">Calculate</button>
<%--                                        <button type="button" id="b2" onClick="submitForm(this, <%=d.getId()%>)" class="btn btn-sm btn-outline-secondary">Order</button>--%>
                                        <input type="hidden" id="command"  name="command">
                                        <input type="hidden" id="productId"  name="productId">

                            </form>
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

    <script>
        function submitForm(x, y){
            if(x.id === "b1") {
                document.getElementById("command").value = "goCalculate";
            }else if(x.id === "b2"){
                document.getElementById("command").value = "goOrder";
            }
            document.getElementById("productId").value = y;

            document.forms.namedItem("foorm").submit();
        }
    </script>
<%@include file="includes/footer.jsp"%>
</body>
</html>