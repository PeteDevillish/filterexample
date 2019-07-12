<%--
  Created by IntelliJ IDEA.
  User: Piotr
  Date: 2019-07-10
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/authorized" method="post">
    Login <br>
    <input type="text" name="login" placeholder="Login"><br>
    Password <br>
    <input type="password" name="password" placeholder="Password">
    <input type="submit">
</form>
</body>
</html>
