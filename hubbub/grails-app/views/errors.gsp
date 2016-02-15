<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
	</head>
	<body>
		<h1>Errors!</h1>
		<h:lameBrowser userAgent="MSIE">
			<p>Dude, Firefox really is better. No, really.</p>
		</h:lameBrowser>
		<g:if env="development">
			<g:renderException exception="${exception}" />
		</g:if>
		<g:else>
			<ul class="errors">
				<li>An error has occurred!!!!</li>
			</ul>
		</g:else>
	</body>
</html>
