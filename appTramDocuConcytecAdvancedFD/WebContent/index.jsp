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
<%@ page import="tramitedoc.concytec.bean.*" %>
<%@ page import="tramitedoc.concytec.dao.sql.*" %>
<%@page import="tramitedoc.concytec.util.GoogleAuthHelper"%>
<style>
html { 
	background: url(img/fondo_nuevo.png) no-repeat fixed center ; 
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
</style>
<!--INCLUYO SCRIPTS ADICIONALES-->
<!-- jsp:include page="/function-theme.jsp"/-->
<!-- FIN DE SCRIPTS ADICIONALES-->  

<% 
	SqlParametroDAO sqlParametroDAO = new SqlParametroDAO();	
	List<BParametro> parametros = sqlParametroDAO.listar();
	String Valor = "";
	
		for(BParametro p : parametros){
			if(p.getSimbolo().equalsIgnoreCase("GLE")){
				Valor = p.getValor();
				break;
			}
		}
			
		if(Valor.equalsIgnoreCase("1")){
			final GoogleAuthHelper helper = new GoogleAuthHelper();
		    response.sendRedirect(helper.buildLoginUrl()); 
		}
	
		
%>

<SCRIPT language="javascript">
	
	function ingreso(){
		form_datos.action="Login.do";
		form_datos.submit();	
	}
	
	function registrarse(){
		form_datos.action="solicitarReg.do";
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

<div id="cent" style="background-image:url(img/logo-front-background-new.png);">
  <div class="clear" id="botones"><br><br>
  <!-- ul>
 <li class="buscar-btn">
  <a href="BusquedaInvestigadores.do?tipo=busqinv"><span>Buscar</span></a>  
  </li>
   <li class="registrar-btn">
  <a href="DirectorioCTI.do?tipo=registrousuario"><span>Registrar</span></a>  
  </li>
  </ul -->
  </div>
  <!--Fomurario de Login-->
  <form name="form_datos" id="form_datos" class="login-form" method="post" action="Login.do" >
	<!-- <div class="clear" id="contexto-form">
		<img src="theme/images/loguito-2013.png" width="32" height="32"/>
	</div> -->
	<!--HEADER-->
	<br></br><br></br><br>
    <div class="header" style="padding-left : 0;">
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