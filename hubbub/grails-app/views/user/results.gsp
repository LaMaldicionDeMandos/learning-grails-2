<%--
  Created by IntelliJ IDEA.
  User: mginart
  Date: 10/02/16
  Time: 09:45
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Search Results</title>
    <meta name="layout" content="main" />
</head>

<body>
    <h1>Results</h1>
    <p>Searched ${org.pasut.learning.grails.User.count()} records for items matching <em>${term}</em>.
    Found <strong>${users.size()}</strong> hits.</p>
    <ul>
        <g:each var="user" in="${users}">
            <li>${user.userId}</li>
        </g:each>
    </ul>
    <g:link action="search">Search Again</g:link>
</body>
</html>