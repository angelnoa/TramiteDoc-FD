<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<html>
<head>
<title>Sistema de Tramite Documentario Concytec--</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<!-- LINK href="css/estilos.css" type="text/css" rel="stylesheet">
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" /-->

	<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
	<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
	<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
	
<!-- Stylesheets -->
	
	<link rel="stylesheet" href="login/css/style-index.css">
	<link rel="stylesheet" href="css/tipografias.css"  type="text/css">
	<link rel="stylesheet" href="css/modelo.css" type="text/css" />
	
<!-- Optimize for mobile devices -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>  
	
<script type="text/javascript">
$(document).ready(function() {
	$(".usuario").focus(function() {
		$(".user-icon").css("left","-48px");
	});
	$(".usuario").blur(function() {
		$(".user-icon").css("left","0px");
	});
	
	$(".password").focus(function() {
		$(".pass-icon").css("left","-48px");
	});
	$(".password").blur(function() {
		$(".pass-icon").css("left","0px");
	});
});
</script>


</head>

<!-- style="background-image:url(img/front-index-nav.gif);" -->
<BODY bgcolor="white">
<div id="container" style="background-color: #e9e9e9;">
<div id="top-bar" >
		<jsp:include page="/headTramite.jsp"/>
</div>
<div style="width: 100%; height:1px; background-color: green; ">
	&nbsp;&nbsp;	
</div>

<!--WRAPPER-->
<div id="wrapper">

	<!--SLIDE-IN ICONS-->
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    <!--END SLIDE-IN ICONS-->

<!--LOGIN FORM-->


<form name="form_datos" id="form_datos" class="login-form" method="post" action="Login.do" >
	
	<!--HEADER-->
    <div class="header">
    <!--TITLE--><h1>Ingreso de Usuarios</h1><!--END TITLE-->
    </div>
    <!--END HEADER-->
	
	<!--CONTENT-->
    <div class="content">
    			  <p><label>Usuario</label>
	<!--USERNAME--><input id="login-form" name="usuario" type="text" class="input username" value="Username" onfocus="this.value=''" /><!--END USERNAME-->
				  </p>	
					
					<p><label>Password</label>
    <!--PASSWORD--><input name="password" type="password" class="input password" value="Password" onfocus="this.value=''" /><!--END PASSWORD-->
    				</p>
    				<p> <font color="#FF0000"> <html:errors property="login"/> </font> </p>	
    </div>
    <!--END CONTENT-->
    
    <!--FOOTER-->
    <div class="footer">
    <!--LOGIN BUTTON--><input type="submit"  name="submit" value="INGRESAR" class="button" /><!--END LOGIN BUTTON-->
    <!--REGISTER BUTTON><input type="submit" name="submit" value="Register" class="register" /><--END REGISTER BUTTON-->
    </div>
    <!--END FOOTER-->

</form>
<!--END LOGIN FORM-->

</div>
<!--END WRAPPER-->

<!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->
</div>
</BODY>
</HTML>