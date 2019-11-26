<%--
  Created by IntelliJ IDEA.
  User: Nout
  Date: 17.11.2019
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Management Screen</title>
</head>
<body>
<div align="center">
    <h1>User List</h1>

    <table border="1">

        <th>Id</th>
        <th>Name</th>
        <th>Role</th>
        <th>Action</th>

        <c:forEach var="user" items="${listUsers}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td><c:forEach var="role" items="${user.userRoles}">
                    ${role.role}
                </c:forEach></td>
                <td><a href="update?id=${user.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=${user.id}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
    <h4>
        <a href="newUser">Add new user</a>
    </h4>
    <a href="<c:url value="/logout" />">Logout</a>
</div>
</body>
</html>