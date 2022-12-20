<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:if test="${Alert != null}">
<div class="alert alert-warning alert-dismissible fade show text-center" role="alert" id="myAlert">
        ${Alert.getMessage()}
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
    ${Alert = null}

</c:if>
