<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.epamproj.dao.entities.Order" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">

<head>
    <%@include file="includes/head.jsp"%>
    <title>Home page</title>
</head>
<body>

<%@include file="includes/navbar.jsp"%>

<div class="container mt-5">
    <ul class="nav nav-tabs nav-fill" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="true">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="invoices-tab" data-toggle="tab" href="#invoices" role="tab" aria-controls="invoices" aria-selected="false">Invoices</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="allOrders-tab" data-toggle="tab" href="#allOrders" role="tab" aria-controls="allOrders" aria-selected="false">Orders history</a>
        </li>
    </ul>

    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active text-center" id="profile" role="tabpanel" aria-labelledby="profile-tab">


            <div class="d-flex justify-content-center align-items-center">

                <div class="card w-50 mt-5">
                    <div class="card-body">
                        <h4 class="py-4">Welcome to your profile, ${user.getName()}
                            <br>
                            <small class="text-muted">Your account information:</small></h4>

                        <h5>Name: <small class="font-weight-light">${user.getName()}</small></h5>
                        <h5>Surname: <small class="font-weight-light">${user.getSurname()}</small></h5>
                        <h5>Patronymic: <small class="font-weight-light">${user.getPatronymic()}</small></h5>
                        <h5>Phone: <small class="font-weight-light">${user.getPhone()}</small></h5>
                        <h5>Login: <small class="font-weight-light">${user.getLogin()}</small></h5>
                        <h5>Cash: <small class="font-weight-light">${user.getCash()}</small></h5>
                        <a class="button mt-2" href="#" data-toggle="modal" data-target="#exampleModalCenter">Top up</a>

                    </div>
                </div>


        </div>
        </div>
        <div class="tab-pane fade text-center" id="invoices" role="tabpanel" aria-labelledby="invoices-tab">
            <table class="table table-loaght">
                <thead>
                <tr>
                    <th scope="col">Invoice number</th>
                    <th scope="col">Type of cargo</th>
                    <th scope="col">Date of creation</th>
                    <th scope="col">Point of departure</th>
                    <th scope="col">Destination</th>
                    <th scope="col">Pay</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" items="${invoices}">
                    <tr>
                        <td>${i.getId()}</td>
                        <td>${i.getOrder().getTypeOfCargo()}</td>
                        <td>${i.getDate()}</td>
                        <td>${i.getOrder().getPointOfDeparture()}</td>
                        <td>${i.getOrder().getDestination()}</td>
                        <td>
                            <form method="get" action="controller">
                                <input name="command" value="goToInvoice" type="hidden">
                                <input name="invoice" value="${i.getId()}" type="hidden">
                                <button type="submit" class="btn btn-primary">Show invoice</button>
                            </form>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>
        <div class="tab-pane fade" id="allOrders" role="tabpanel" aria-labelledby="allOrders-tab">
            <table class="table table-loaght">
                <thead>
                <tr>
                    <th scope="col">Order number</th>
                    <th scope="col">Type of cargo</th>
                    <th scope="col">Date</th>
                    <th scope="col">Point of departure</th>
                    <th scope="col">Destination</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <td>${o.getId()}</td>
                        <td>${o.getTypeOfCargo()}</td>
                        <td>${o.getDate()}</td>
                        <td>${o.getPointOfDeparture()}</td>
                        <td>${o.getDestination()}</td>
                        <td>${o.getStatus()}</td>

                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>

    </div>

</div>

<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="controller" method="post" name="form1">
                    <input name="command" value="topUp" type="hidden">
                    <label for="recipient-name" class="col-form-label">Enter summ:</label>
                    <input type="text" class="form-control" id="recipient-name" name="money" pattern="([0-9]+[.])?[0-9]+" required>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="submit()">Submit</button>
            </div>
        </div>
    </div>
</div>



<%@include file="includes/footer.jsp"%>

<script>
    function submit() {
        document.forms.namedItem('form1').submit();
    }
</script>

<style>
    .myButton {
        box-shadow:inset 0px 0px 14px -3px #fce2c1;
        background:linear-gradient(to bottom, #ffc477 5%, #fb9e25 100%);
        background-color:#ffc477;
        border-radius:6px;
        border:1px solid #eeb44f;
        display:inline-block;
        cursor:pointer;
        color:#ffffff;
        font-family:Arial;
        font-size:15px;
        font-weight:bold;
        padding:6px 24px;
        text-decoration:none;
        text-shadow:0px 1px 0px #cc9f52;
    }
    .myButton:hover {
        background:linear-gradient(to bottom, #fb9e25 5%, #ffc477 100%);
        background-color:#fb9e25;
    }
    .myButton:active {
        position:relative;
        top:1px;

    }

</style>
<style>
    .button {
        display: inline-block;
        text-align: center;
        vertical-align: middle;
        padding: 2px 16px;
        border: 1px solid #035432;
        border-radius: 4px;
        background: #069e5e;
        background: -webkit-gradient(linear, left top, left bottom, from(#069e5e), to(#035432));
        background: -moz-linear-gradient(top, #069e5e, #035432);
        background: linear-gradient(to bottom, #069e5e, #035432);
        font: normal normal normal 20px arial;
        color: #ffffff;
        text-decoration: none;
    }
    .button:hover,
    .button:focus {
        border: 1px solid ##04633b;
        background: #07be71;
        background: -webkit-gradient(linear, left top, left bottom, from(#07be71), to(#04653c));
        background: -moz-linear-gradient(top, #07be71, #04653c);
        background: linear-gradient(to bottom, #07be71, #04653c);
        color: #ffffff;
        text-decoration: none;
    }
    .button:active {
        background: #035432;
        background: -webkit-gradient(linear, left top, left bottom, from(#035432), to(#035432));
        background: -moz-linear-gradient(top, #035432, #035432);
        background: linear-gradient(to bottom, #035432, #035432);
    }
    .button:after{
        content:  "\0000a0";
        display: inline-block;
        height: 24px;
        width: 24px;
        line-height: 24px;
        margin: 0 -4px -6px 4px;
        position: relative;
        top: 0px;
        left: 1px;
        background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAA7EAAAOxAGVKw4bAAAE9UlEQVRIibWVXWxTZRjHf+f0tD2n69at7dat62BjbIMxhkACTMDJRwSC4onEmAiIAglRiHqh0QvkjisNoEIMCQSCwcREk8qHDASHxDDYRGDA6GBfjkk3tq6l2/q1tseLQXV8ZYn6XP7f5/x/ed/3/7wH/ucSkt5jKvDJv/D4Vcxb/t6TFiXADsx4IIQjMRTZkGoYCkW50dpDIBiiIC+LsgmOhz0MDwsPA1KlaRpbdx5jnNOKSdbTfece+oEEU7Ks2BSZL45c4vNtryHpxH9+ZhozQBAEqqZPwNgyxHQ5m5yJ49GLI2YdwSCVlQUPmwMEngZ4pLt69kRuBPzkm80pc4Da210sqCp9nEfTmHcA0B8I0R4d5IfWNkRBuK9qNPjuMj0y/DiPE2MGDIVjfH20mfUb30VImY/UBuDHmhrs1jTyHZkP5D40Do8ZoJdESETxeDyPAAAGBwKY5FGh+VR0Lg+OGRBPJDFqMca1teI0m0c1+iMRboSDaH9LZwWS259mPgqgaRpfHTyLai2gwm57tNNiwWEysWtfLR9tXnLZaJBeFfJeio8FUA90/Fx3s3BCxEiFy0Y4Hud4RwdNfj9JNIrTLSwvKqQgPZ1FGbms/eBgTWw4IaqquhmYbTBILkmvM0TCsUAyqd1k5OJPud3uuACwf/dWp+fKn63bZs2R/ZEIO5qvUb1yJq5xdiKRYQYHwtR8e4ENrokUWSxsOXculsw1JZ9fPFUuLs0jwzIya4l4km6vnysX26g92fjbvXuhZToAwZC1dlVJ2Yoii4Vd16+yYtNCnC4rB/ac4vLFNuZWlzNzXhn7j9SxINfJ6Z47uk1bXpZc4+wYZX3qOERRIMNiomRSPnq95Lze2HlDp6oqFqPxy7crp+YHolGupMeYv3AKTY2dmNMV5i+cQlenj+KSXO4GBpH7otR7u2ls6gRBQFEMyIoBQRCIRoa5/Ucfv5y+xvkz1xgcih2XgNwKm22GLEm0BYPk5I9kvLQ8n7o9Hlpuenn9zWoAcp1Z9HTcJdMi8/6aadQ3eqn97ha+QARN05CNEs4cM89OzmGKfTKf7atHAipLMjMlAKss47vbA4C3q5/qxRWEwzEa6m6xaOk0+nqDzFBk+lpDnDrXwQvzilhUNX5UajQN2rsCHDrclEqRM1tRAHAoCqHL/fj6Bsh2WNi7+wTxeJI16xcQCkVpvtDO6qkzsWUqWMxGdh5oIBpLkJ5mQJJEQqFhIrEE+Q4z08sdeNp8SIokeeyKnAREQRBYV1LGrp0nePGNuaxat4DwUJREIsne7cdZU1CMJIoMhYeZVGxjYdV4NA0GQzHiiSRpih6DXkcyqfHNkfs7CMfjLbcC9wZLs7IyAJxmMx+WTsV96BKd8TCaAA4MvDOhBGdaWiot+7+/iiJLlBVasVsVJN0I+LY3SHN7Pzpx5KmRNE3rP+TxnHGZzSumZdsByDIaeau8/InTqRgkPt44B58/TOttP739IeIJjTRFzzOTHaxcWkb9FS+Nzb0jg6aqaoYoCDtm5uSsrsy2G5xpZjIM+tT/IK5pDMaG8Q4NcdXXF6vv7jlXUZY9a9lzRabSIiuKrEcUIJ7Q6O0P0XDVy9Halt8DweiSUU+mqqq5wBygELAB8v2lGOAHOoDzbre7S1VVF/CKTidUpacZXTpRkMLReDAUHvYAPwEn3W537InH8F/VX7rm4ee0Ic0bAAAAAElFTkSuQmCC") no-repeat left center transparent;
        background-size: 100% 100%;
    }


</style>
</body>
</html>
