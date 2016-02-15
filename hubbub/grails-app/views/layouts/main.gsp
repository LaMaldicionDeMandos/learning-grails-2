<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Hubbub &raquo;<g:layoutTitle default="Welcome"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="http://yui.yahooapis.com/2.5.1/build/reset-fonts-grids/reset-fonts-grids.css"
			  type="text/css">
		<g:external dir="css" file="hubbub.css"/>
		<g:external dir="css" file="main.css"/>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
	</head>
	<body>
		<div id="doc3" class="yui-t4">
			<div id="hd">
				<a href="<g:createLinkTo dir='/'/>">
					<img id="logo" src="${createLinkTo(dir: 'images', file: 'grails_logo.png')}" alt="hubbubLogo" />
				</a>
			</div>
		</div>
		<div id="bd">
			<div id="yui-main">
				<div class="yui-b">
					<div class="yui-g">
						<g:layoutBody />
					</div>
				</div>
			</div>
			<div class="yui-b">ALGO</div>
		</div>

		<div id="ft">
			<div id="footerText">Hubbub - Social Networking on Grails Version <g:meta name="app.version" /></div>
		</div>
	</body>
</html>
