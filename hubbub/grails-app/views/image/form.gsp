<%--
  Created by IntelliJ IDEA.
  User: mginart
  Date: 11/02/16
  Time: 15:03
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <g:uploadForm action="upload">
        User Id:
        <g:select name="userId" from="${userList}" optionKey="userId" optionValue="userId"/>
        <p/>
        Photo: <input name="photo" type="file" />
        <g:submitButton name="upload" value="Upload"/>
    </g:uploadForm>
</body>
</html>