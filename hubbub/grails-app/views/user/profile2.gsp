<%--
  Created by IntelliJ IDEA.
  User: mginart
  Date: 11/02/16
  Time: 15:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <div>
        <g:if test="${profile.photo}">
            <img src="<g:createLink controller='image' action='renderImage' id='${userId}'/>" />
        </g:if>
        <p>Profile for <strong>${profile.fullName}</strong></p>
        <p>Bio: ${profile.bio}</p>
    </div>
    <g:link controller="user" action="profile" id="${userId}">Go to original Profile</g:link>
</body>
</html>