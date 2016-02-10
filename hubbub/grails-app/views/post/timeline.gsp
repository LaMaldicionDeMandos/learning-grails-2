<%--
  Created by IntelliJ IDEA.
  User: mginart
  Date: 10/02/16
  Time: 15:57
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Timeline for ${user.profile.fullName}</title>
    <meta name="layout" content="main" />
</head>

<body>
    <h1>Timeline for ${user.profile.fullName}</h1>
    <div class="allPosts">
        <g:each var="post" in="${user.posts}">
            <div class="postEntry">
                <div class="postText">${post.content}</div>
                <div class="postDate">${post.dateCreated}</div>
            </div>
        </g:each>
    </div>
</body>
</html>