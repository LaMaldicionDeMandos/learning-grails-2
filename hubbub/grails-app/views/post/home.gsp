<%--
  Created by IntelliJ IDEA.
  User: mginart
  Date: 12/02/16
  Time: 14:56
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div class="allPosts">
    <h:posts in="${posts}">
        <div class="postEntry">
            <div class="postText">${post.content}</div>
            <div class="postDate"><h:dateFromNow date="${post.dateCreated}" ></h:dateFromNow></div>
        </div>
    </h:posts>
    <g:paginate total="${postsSize}" max="2"/>
</div>
</body>
</html>