<%@ include file="taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="language" content="es" />

<link href="theme/css3/style.css" rel="stylesheet" type="text/css" />
<link href="theme/css3/style-adicional.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/tipografias.css"  type="text/css">

<!--INCLUYO SCRIPTS ADICIONALES-->
<!-- jsp:include page="/function-theme.jsp"/-->
<!-- FIN DE SCRIPTS ADICIONALES-->  

<title>Sistema de Tramite Documentario **-CONCYTEC-**</title>
<!--TERMINA EL HEAD-->
</head>
<body>


<!--EMPIEZA EL CONTENEDOR DEL PAGE-->
<div id="page">
<!--INCLUYO EL MENU-->
<!-- jsp:include page="/inc_header.jsp"/-->
<jsp:include page="/headTramite.jsp"/>

<div class="clear" id="content">

<div id="cent" style="background-image:url(img/logo-front-backgroundv4.png);">
  <div class="clear" id="botones"><br><br>
  <!-- ul>
 <li class="buscar-btn">
  <a href="BusquedaInvestigadores.do?tipo=busqinv"><span>Buscar</span></a>  
  </li>
   <li class="registrar-btn">
  <a href="DirectorioCTI.do?tipo=registrousuario"><span>Registrar</span></a>  
  </li>
  </ul-->
  </div>
  <div class="login-form">
  <div class="clear" id="contexto-form">
		<img src="theme/images/loguito-2013.png" width="32" height="32"/>
	</div>
	<!--HEADER-->
    <div class="header">
    <!--TITLE--><h1>Ingreso de Usuarios</h1><!--END TITLE-->
    </div>
    <!--END HEADER-->
    <br />
    <br />
    <br />
  	<div class="content">
  	<b><label>Ha iniciado sesión correctamente con google pero su usuario no tiene acceso al sistema.<br/>Contacte con el área de sistemas.</label></b>
  	</div>
  </div>

</div>
</div>
<!--INCLUYO EL FOOTER-->
<jsp:include page="/inc_footer.jsp"/>
</div>
</body>
</html>