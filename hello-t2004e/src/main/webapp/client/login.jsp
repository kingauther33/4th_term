<%--
  Created by IntelliJ IDEA.
  User: kinga
  Date: 12/20/2021
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    <div>
        <label>Username: </label>
        <input type="text" name="username">
    </div>
    <div>
        <label>Email: </label>
        <input type="password" name="password">
    </div>

    <button type="submit">Submit</button>
</form>
</body>
</html>
