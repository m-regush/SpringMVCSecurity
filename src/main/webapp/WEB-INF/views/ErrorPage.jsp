<%--
  Created by IntelliJ IDEA.
  User: Nout
  Date: 20.11.2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>HTTP Status 403 - Access is denied</h1>

<c:choose>
    <c:when test="${empty name}">
        <h2>You do not have permission to access this page!</h2>
    </c:when>
    <c:otherwise>
        <h2>Username : ${name} <br/>You do not have permission to access this page!</h2>
    </c:otherwise>
</c:choose>

</body>
</html>
