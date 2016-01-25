<%@ include file="taglibs.jsp" %>
<html>
<head>
<title>Sistema de Tramite Documentario Concytec--</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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
	
<!-- Optimize for mobile devices -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>  
	
<script type="text/javascript">
$(document).ready(function() {
	$(".username").focus(function() {
		$(".user-icon").css("left","-48px");
	});
	$(".username").blur(function() {
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

<SCRIPT language="javascript">
	
	function ingreso(){
		form_datos.action="Login.do";
		form_datos.submit();	
	}
</SCRIPT>
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

<div id="cent" style="background-image:url(img/nt_back_new.png);">
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
  <!--Fomurario de Login-->
  <form name="form_datos" id="form_datos" class="login-form" method="post" action="Login.do" >
	<div class="clear" id="contexto-form">
		<img src="theme/images/logo_cubo.gif" width="32" height="32"/>
	</div>
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
    				<div style="color:red; font-size:9pt; font-weight: bold; line-height: 9px; ">&nbsp;<html:errors property="login"/></div>
    					
    </div>
    <!--END CONTENT-->
    
    <!--FOOTER-->
    <div class="footer">
    <!--LOGIN BUTTON--><input type="submit"  name="submit" value="INGRESAR" class="button" /><!--END LOGIN BUTTON-->
    <!--REGISTER BUTTON><input type="submit" name="submit" value="Register" class="register" /><--END REGISTER BUTTON-->
    </div>
    <!--END FOOTER-->

	</form>
  <!--Fin de Formulario de Login-->
  

</div>
</div>
<!--INCLUYO EL FOOTER-->
<jsp:include page="/inc_footer.jsp"/>
</div>
</body>
</html>