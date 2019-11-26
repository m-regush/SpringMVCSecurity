<%--
  Created by IntelliJ IDEA.
  User: Nout
  Date: 17.11.2019
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Contact</title>
</head>
<body>
<div align="center">
    <h1>Update User</h1>
    <form:form action="update" method="post" modelAttribute="user">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" value="" name="password"> </td>
            </tr>
            <tr>
               <td> Role
                <input type="checkbox" name="role" value="ADMIN">ADMIN
                <input type="checkbox" name="role" value="USER">USER

            </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
    <button onclick="location.href='/admin/users'">Back</button>
</div>
</body>
</html>
