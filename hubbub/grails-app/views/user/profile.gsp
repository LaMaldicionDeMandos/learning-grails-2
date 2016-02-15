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
        <h:tinyThumbnail userId="${userId}" />
        <br/>
        <p>Profile for <strong>${profile.fullName}</strong></p>
        <p>Bio: ${profile.bio}</p>
    </div>
</body>
</html>