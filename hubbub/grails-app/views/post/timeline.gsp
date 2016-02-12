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
    <div id="newPost">
        <h3>What is ${user.profile.fullName} hacking on right now?</h3>
        <p>
            <g:form action="addPost" id="${params.id}">
                <g:textArea id="postContent" name="content" rows="3" cols="50" />
                <br />
                <g:submitButton name="post" value="Post" />
            </g:form>
        </p>
    </div>
    <div class="allPosts">
        ${user.posts.size()}
        <g:each var="post" in="${user.posts}">
            <div class="postEntry">
                <div class="postText">${post.content}</div>
                <div class="postDate">${post.dateCreated}</div>
            </div>
        </g:each>
        <g:paginate total="${user.posts.size()}" max="2"/>
    </div>
<g:if test="${flash.message}"> <div class="flash">
    ${flash.message}
</div>
</g:if>
</body>
</html>