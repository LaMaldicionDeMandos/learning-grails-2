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
    <g:javascript library="jquery" plugin="jquery"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='${user.profile.skin}.css'/>">
</head>

<body>
<g:javascript>
    function clearPost(data) {
        $('postContent').value='';
    }

    function showSpinner(visible) {
        var spinner = $('spinner');
        var style = spinner.css('display', visible ? 'inline' : 'none');
    }
</g:javascript>
    <h1>Timeline for ${user.profile.fullName}</h1>
    <div id="newPost">
        <h3>What is ${user.profile.fullName} hacking on right now?</h3>
        <p>
            <g:formRemote name="${params.id}"
                          url="[controller: 'post', action: 'addAjaxPost']"
                          update="allPosts"
                          onSuccess="clearPost(data)"
                          onLoading="showSpinner(true)"
                          onComplete="showSpinner(false)">
                <g:hiddenField name="userId" value="${params.id}" />
                <g:textArea id="postContent" name="content" rows="3" cols="50" />
                <br />
                <g:submitButton name="post" value="Post"/>
                <img id="spinner" style="display: none" src="<g:createLinkTo dir='/images/' file='spinner.gif'/>"/>
            </g:formRemote>
        </p>
    </div>
    <div id="allPosts" class="allPosts">
        ${user.posts.size()}
        <g:each var="post" in="${user.posts}">
            <div class="postEntry">
                <div class="postText">${post.content}</div>
                <div class="postDate"><h:dateFromNow date="${post.dateCreated}" /></div>
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