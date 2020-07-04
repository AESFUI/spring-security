<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Index</title>
</head>
<body>

<h1>Кря</h1>

<sec:authorize access="isAuthenticated()">
    <p>USER: <sec:authentication property="name"/></p>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
    <p>
        <a href="/login">GOTO LOGIN</a>
    </p>
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
    <p>
        <a href="/admin">GOTO ADMIN PAGE</a>
    </p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <p>
        <a href="/logout">GOTO LOGOUT</a>
    </p>
</sec:authorize>

</body>
</html>